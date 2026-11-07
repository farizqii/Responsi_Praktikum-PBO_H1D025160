# Turn Based RPG – Responsi Akhir PBO

## Identitas
- **Nama**   : Adyan Damar Farizqi
- **NIM**    : H1D025160
- **Tema**   : Game – Turn Based RPG

## Deskripsi
Game RPG bergiliran: party (Ksatria, Penyihir, Pendeta) melawan Naga Api.
Menu 1 membuktikan 10 persyaratan OOP, Menu 2 adalah pertarungan interaktif.

## Struktur Class
| File | Peran |
|---|---|
| `Karakter.java` | Superclass **abstract** (abstract method `serang`) |
| `Ksatria.java`, `Penyihir.java`, `Pendeta.java`, `Monster.java` | Subclass |
| `PemakaiSkill.java`, `Bertahan.java`, `Penyembuhan.java` | Interface |
| `Main.java` | Class utama + alur pengujian |

## Cara Compile & Run (Terminal)
Pastikan JDK terpasang (`javac -version`).
```bash
cd RPG-Game
javac *.java
java Main
```

## Screenshot Luaran
(Tempel screenshot hasil Menu 1 dan Menu 2 di sini)