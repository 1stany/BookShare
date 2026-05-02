Write-Host "=== BOOKSHARE LAUNCHER (Windows) ==="

###############################################################################
# FUNZIONI
###############################################################################

###############################################################################
# AVVIO AUTOMATICO DI DOCKER DESKTOP (ricerca intelligente dell'eseguibile)
###############################################################################

function Start-DockerDesktop {
    Write-Host "Checking Docker daemon state..."

    # 1) Verifica se il daemon Docker è già attivo
    docker info >$null 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Docker daemon is already running."
        return
    }

    Write-Host "Docker daemon is NOT running. Avvio Docker Desktop..."

    # 2) Percorsi possibili dell'eseguibile Docker Desktop
    $possiblePaths = @(
        "$Env:ProgramFiles\Docker\Docker\Docker Desktop.exe",
        "$Env:ProgramFiles\Docker\Docker Desktop\Docker Desktop.exe",
        "$Env:LocalAppData\Docker\Docker Desktop.exe",
        "$Env:LocalAppData\Programs\Docker\Docker Desktop.exe"
    )

    # 3) Trova il primo percorso valido
    $dockerExe = $possiblePaths | Where-Object { Test-Path $_ } | Select-Object -First 1

    if (-not $dockerExe) {
        Write-Host "ERRORE: impossibile trovare Docker Desktop sul sistema."
        Write-Host "Avvialo manualmente e riprova."
        exit 1
    }

    Write-Host "Trovato Docker Desktop:"
    Write-Host "  $dockerExe"
    Start-Process $dockerExe

    # 4) Attesa che il daemon Docker si avvii
    Write-Host "Attesa avvio Docker daemon..."

    $maxAttempts = 30
    for ($i = 1; $i -le $maxAttempts; $i++) {
        docker info >$null 2>&1
        if ($LASTEXITCODE -eq 0) {
            Write-Host "Docker daemon is now running."
            return
        }

        Write-Host "Docker non è ancora pronto... ($i/$maxAttempts)"
        Start-Sleep -Seconds 2
    }

    Write-Host "ERRORE: Docker Desktop non si è avviato in tempo."
    exit 1
}

# Verifica se un'immagine Docker esiste
function Image-Exists($imageName) {
    docker image inspect $imageName >$null 2>&1
    return ($LASTEXITCODE -eq 0)
}

# Verifica se i container del progetto sono running
function Are-Containers-Running {
    try {
        $containers = docker compose ps --format json | ConvertFrom-Json
    } catch {
        return $false
    }

    $expected = @("bookshare-fe", "bookshare-be", "bookshare-db")

    foreach ($name in $expected) {
        $c = $containers | Where-Object { $_.Name -eq $name }
        if (-not $c) { return $false }
        if ($c.State -ne "running") { return $false }
    }

    return $true
}

# Attende che tutti i container siano running
function Wait-For-Containers {
    param(
        [int]$timeoutSeconds = 60
    )

    $expected = @("bookshare-fe", "bookshare-be", "bookshare-db")
    $start = Get-Date

    Write-Host "Waiting for containers to be running..."

    while ((Get-Date) - $start -lt (New-TimeSpan -Seconds $timeoutSeconds)) {

        try {
            $containers = docker compose ps --format json | ConvertFrom-Json
        } catch {
            Start-Sleep -Seconds 1
            continue
        }

        $allRunning = $true

        foreach ($name in $expected) {
            $c = $containers | Where-Object { $_.Name -eq $name }

            if (-not $c) { 
                $allRunning = $false
                break
            }

            if ($c.State -ne "running") {
                $allRunning = $false
                break
            }
        }

        if ($allRunning) {
            Write-Host "All containers are running."
            return $true
        }

        Start-Sleep -Seconds 1
    }

    Write-Host "Timeout waiting for containers."
    return $false
}

###############################################################################
# MAIN
###############################################################################

# 1) Avvio automatico Docker Desktop
Start-DockerDesktop

# 2) Controllo Docker installato
if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
    Write-Host "Docker non è installato."
    exit 1
}

# 3) Attesa Docker daemon
Write-Host "Checking Docker daemon..."

$maxAttempts = 20
$attempt = 0

while ($attempt -lt $maxAttempts) {
    docker info >$null 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Docker is running."
        break
    }

    Write-Host "Docker non è ancora pronto... (tentativo $($attempt+1)/$maxAttempts)"
    Start-Sleep -Seconds 2
    $attempt++
}

if ($LASTEXITCODE -ne 0) {
    Write-Host "Docker non si è avviato. Avvialo manualmente e riprova."
    exit 1
}

# 4) Build intelligente
Write-Host "Checking images..."

$images = @("projectwork-frontend", "projectwork-backend")
$needBuild = $false

foreach ($img in $images) {
    if (-not (Image-Exists $img)) {
        Write-Host "Missing image: $img"
        $needBuild = $true
    }
}

if ($needBuild) {
    Write-Host "Building missing images..."
    docker compose build
} else {
    Write-Host "Images already exist. Skipping build."
}

# 5) Avvio container
Write-Host "Starting containers..."
docker compose up -d

# 6) Attesa container
if (Wait-For-Containers -timeoutSeconds 60) {
    Write-Host "Containers ready."
} else {
    Write-Host "WARNING: Some containers did not reach 'running' state."
}

# 7) Apertura browser
$url = "http://localhost:4200"
Write-Host "Opening browser: $url"
Start-Process $url

Write-Host "=== PROJECT READY ==="
exit 0
