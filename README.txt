# ProjectWork – Launcher Universale

Questo progetto include un launcher compatibile con Windows, Linux e macOS.
Il launcher si occupa di:

- rileva automaticamente il sistema operativo
- esegue lo script corretto (`.ps1` su Windows, `.sh` su Linux/macOS)
- verifica la presenza delle immagini Docker
- esegue la build solo se necessario
- avvia i container tramite Docker Compose
- apre automaticamente il browser su `http://localhost:4200`

## 📚 Indice

- [Prerequisiti](#-prerequisiti)
- [Struttura del progetto](#-struttura-del-progetto)
- [Avvio su Windows](#-avvio-su-windows)
- [Avvio su Linux--macos](#-avvio-su-linux--macos)
- [Troubleshooting](#️-troubleshooting)
- [Autori](#-autori)
- [Licenza](#-licenza)

---

## 📦 Prerequisiti

Per eseguire correttamente il progetto sono necessari:

- **Docker** (Docker Desktop su Windows/macOS, Docker Engine su Linux)
- **Docker Compose** (integrato in Docker Desktop)
- **PowerShell**  
  - Windows: già incluso  
  - Linux/macOS: opzionale (lo script usa automaticamente Bash se non disponibile)
- **Porte libere**:
  - 4200 → frontend Angular
  - 8080 → backend Spring Boot
  - 3306 → database MySQL

Se una di queste porte è occupata, Docker potrebbe non riuscire ad avviare i container.

---

## 📁 Struttura del progetto

📁 BookShare
 ├─ launch.bat                   # Launcher Windows, permette di avviare il progetto su windows
 ├─ README.txt                   # ProjectWork – Launcher Universale
 ├─ Docs                         # Relazione tecnica
 └─ ProjectWork/
    ├─ start-project             # Launcher universale (Linux/macOS/Windows via Git Bash)
    ├─ launcher.ps1              # Launcher Windows
    ├─ start-project.sh          # Launcher Linux/macOS
    ├─ docker-compose.yml        # Configurazione Docker
    ├─ frontend/                 # Applicazione Angular
    └─ backend/                  # Applicazione Spring Boot

---

## 🟦 Avvio su Windows

1. Apri la cartella principale del progetto.
2. Fai doppio click sul collegamento **launch.bat**  
   *(oppure esegui manualmente `launch.ps1`)*.

Il launcher:
-entra automaticamente nella cartella ProjectWork
- controlla le immagini
- builda solo se necessario
- avvia i container
- apre il browser

---

## 🟩 Avvio su Linux / macOS

1. Apri il terminale nella cartella del progetto:

   BookShare/ProjectWork

2. Rendi eseguibili gli script (solo la prima volta):

   chmod +x start-project
   chmod +x start-project.sh

3. Avvia il progetto:

   ./start-project

---

## Credenziali di test
   amelia@gmail.com           user
   Amelia                     password


## 🛠️ Troubleshooting

### ❗ Errore: "port is already allocated"
Una delle porte richieste dal progetto è già in uso.  
Soluzione:
- Chiudi l’applicazione che usa la porta
- Oppure modifica la porta nel `docker-compose.yml`

---

### ❗ Errore: "docker: command not found"
Docker non è installato o non è nel PATH.  
Soluzione:
- Installa Docker Desktop (Windows/macOS)
- Installa Docker Engine (Linux)
- Riavvia il terminale

---

### ❗ Errore: "permission denied" su Linux/macOS
Gli script non sono eseguibili.  
Soluzione:

```bash
chmod +x start-project
chmod +x start-project.sh

---

## 👤 Autori

- Stanislao — Università Informatica L-31

## 📄 Licenza

Questo progetto è distribuito per scopi didattici e dimostrativi.
