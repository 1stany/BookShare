#!/usr/bin/env bash

echo "=== BOOKSHARE LAUNCHER (Linux/macOS) ==="

if ! command -v docker >/dev/null 2>&1; then
    echo "Docker non è installato."
    exit 1
fi

echo "Building images..."
docker compose build

echo "Starting containers..."
docker compose up -d

if command -v xdg-open >/dev/null 2>&1; then
    xdg-open http://localhost:4200
elif command -v open >/dev/null 2>&1; then
    open http://localhost:4200
else
    echo "Apri manualmente: http://localhost:4200"
fi

echo "=== PROJECT READY ==="
exit 0
