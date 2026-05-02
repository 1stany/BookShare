@echo off
setlocal

REM Cartella principale del progetto
set BASE=%~dp0

REM Cartella dove si trova docker-compose.yml
set PROJECT_DIR=%BASE%ProjectWork

REM File PowerShell da eseguire
set SCRIPT=%PROJECT_DIR%\launcher.ps1

echo BASE: %BASE%
echo PROJECT_DIR: %PROJECT_DIR%
echo SCRIPT: %SCRIPT%

if not exist "%SCRIPT%" (
    echo ERRORE: impossibile trovare lo script PowerShell.
    pause
    exit /b 1
)

cd /d "%PROJECT_DIR%"
echo Directory corrente:
cd

powershell.exe -ExecutionPolicy Bypass -File "%SCRIPT%"

echo.
echo === FINE ESECUZIONE ===
pause
