###############################################################################
# FUNZIONE: verifica se i container del progetto sono già in esecuzione
###############################################################################
function Are-Containers-Running {
    try {
        $containers = docker compose ps --format json | ConvertFrom-Json
    } catch {
        return $false
    }

    # Nomi dei container che devono essere attivi
    $expected = @("bookshare-fe", "bookshare-be", "bookshare-db")

    foreach ($name in $expected) {
        $c = $containers | Where-Object { $_.Name -eq $name }

        # Se il container non esiste → non è in esecuzione
        if (-not $c) { return $false }

        # Se non è in stato "running" → non è in esecuzione
        if ($c.State -ne "running") { return $false }
    }

    return $true
}
