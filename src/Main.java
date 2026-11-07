import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Class Main (dipisahkan dari class lain) - [Konsep 4].
 * Menu 1: demo otomatis pembuktian 10 persyaratan.
 * Menu 2: pertarungan turn-based interaktif.
 */
public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final Random acak = new Random();

    public static void main(String[] args) {
        boolean jalan = true;
        while (jalan) {
            System.out.println("\n=====================================");
            System.out.println("   TURN BASED RPG - H1D025160");
            System.out.println("=====================================");
            System.out.println("1. Demo Pembuktian 10 Persyaratan");
            System.out.println("2. Mulai Pertarungan");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            String pilihan = input.nextLine().trim();

            switch (pilihan) {
                case "1":
                    demoPersyaratan();
                    break;
                case "2":
                    mulaiPertarungan();
                    break;
                case "0":
                    jalan = false;
                    System.out.println("Terima kasih telah bermain!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // ================== DEMO 10 PERSYARATAN ==================
    private static void demoPersyaratan() {
        Ksatria arthur = new Ksatria("Arthur");
        Penyihir merlin = new Penyihir("Merlin");
        Pendeta luna = new Pendeta("Luna");
        Monster goblin = new Monster("Goblin Raja", "Goblin", 200, 14, 4);

        judul("1. State dan Behavior");
        arthur.tampilkanStatus();                                  // method void
        int dmg = arthur.hitungDamage(10, goblin);                 // method fungsi (return value)
        System.out.println("Hasil hitungDamage(10) Arthur -> Goblin = " + dmg);
        arthur.terimaDamage(20);                                   // method void mengubah state

        judul("2. Immutability");
        System.out.println("Konstanta global PERSEN_HP_KRITIS = " + Karakter.PERSEN_HP_KRITIS);
        System.out.println("Atribut final id (tanpa setter) Arthur = " + arthur.getId());

        judul("3. Enkapsulasi dan Overloading");
        arthur.setNama("Sir Arthur");                              // setter
        System.out.println("Nama lewat getter: " + arthur.getNama());
        Ksatria ksatriaKuat = new Ksatria("Lancelot", 9);          // constructor overload (2 parameter)
        Ksatria ksatriaBiasa = new Ksatria("Gawain");              // constructor overload (1 parameter)
        System.out.println(ksatriaKuat.getNama() + " kekuatan pedang " + ksatriaKuat.getKekuatanPedang()
                + " | " + ksatriaBiasa.getNama() + " kekuatan pedang " + ksatriaBiasa.getKekuatanPedang());

        judul("4. Inheritance");
        System.out.println("Ksatria, Penyihir, Pendeta, Monster semuanya extends Karakter.");
        System.out.println("Arthur instanceof Karakter? " + (arthur instanceof Karakter));

        judul("5. Super Chaining");
        System.out.println("super(...) dipakai di konstruktor semua subclass (id otomatis: "
                + merlin.getId() + ", " + luna.getId() + ").");
        System.out.println("super.tampilkanStatus() dipanggil dalam override:");
        merlin.tampilkanStatus();

        judul("6. Method Overriding");
        System.out.println("Ksatria meng-override terimaDamage() (efek bertahan 50%):");
        arthur.bertahan();
        arthur.terimaDamage(40);

        judul("7. Abstract Class");
        System.out.println("Karakter bersifat abstract dengan abstract method serang(Karakter).");
        System.out.println("Karakter tidak bisa di-new; hanya subclass yang bisa.");

        judul("8. Logika State Percabangan (if/else)");
        System.out.println("Merlin MP awal: " + merlin.getMp());
        for (int i = 1; i <= 5; i++) {
            System.out.println("-- Giliran " + i + " (MP " + merlin.getMp() + ")");
            merlin.serang(goblin);
        }
        System.out.println("Goblin HP kritis? " + goblin.isHpKritis());

        judul("9. Polimorfisme Interface");
        PemakaiSkill[] pemakaiSkill = { new Ksatria("Arthur"), new Penyihir("Merlin"), new Pendeta("Luna") };
        Monster dummy = new Monster("Boneka Latihan", "Dummy", 500, 1, 0);
        for (PemakaiSkill p : pemakaiSkill) {
            System.out.println("Skill: " + p.getNamaSkill());
            p.gunakanSkill(dummy);                                 // dipanggil lewat tipe interface
        }

        judul("10. Multiple Interface");
        System.out.println("Ksatria implements PemakaiSkill, Bertahan");
        System.out.println("Pendeta implements PemakaiSkill, Penyembuhan");
        Penyembuhan penyembuh = luna;                              // referensi interface kedua
        Karakter terluka = new Ksatria("Prajurit");
        terluka.setHp(30);
        penyembuh.sembuhkan(terluka);

        System.out.println("\n[Demo selesai]");
    }

    private static void judul(String teks) {
        System.out.println("\n--- [Konsep " + teks + "] ---");
    }

    // ================== PERTARUNGAN INTERAKTIF ==================
    private static void mulaiPertarungan() {
        List<Karakter> party = new ArrayList<>();
        party.add(new Ksatria("Arthur"));
        party.add(new Penyihir("Merlin"));
        party.add(new Pendeta("Luna"));
        Monster naga = new Monster("Naga Api", "Naga", 350, 20, 6);

        System.out.println("\nSeekor " + naga.getNama() + " muncul! Pertarungan dimulai!");
        int ronde = 1;

        while (naga.isHidup() && masihAdaYangHidup(party)) {
            System.out.println("\n========== RONDE " + ronde + " ==========");
            naga.tampilkanStatus();
            for (Karakter k : party) {
                k.tampilkanStatus();
            }

            for (Karakter hero : party) {
                if (!hero.isHidup() || !naga.isHidup()) {
                    continue;
                }
                giliranHero(hero, naga, party);
            }

            if (naga.isHidup()) {
                System.out.println("\n--- Giliran " + naga.getNama() + " ---");
                Karakter target = pilihTargetAcak(party);
                naga.serang(target);
            }
            ronde++;
        }

        if (naga.isHidup()) {
            System.out.println("\nSEMUA PAHLAWAN GUGUR. GAME OVER.");
        } else {
            System.out.println("\n" + naga.getNama() + " dikalahkan! KAMU MENANG dalam " + (ronde - 1) + " ronde!");
        }
    }

    private static void giliranHero(Karakter hero, Monster musuh, List<Karakter> party) {
        System.out.println("\n--- Giliran " + hero.getNama() + " ---");
        System.out.println("1. Serang (otomatis)");
        System.out.println("2. Skill");
        if (hero instanceof Bertahan) {
            System.out.println("3. Bertahan");
        } else if (hero instanceof Penyembuhan) {
            System.out.println("3. Sembuhkan sekutu");
        }
        System.out.print("Pilih: ");
        String pilihan = input.nextLine().trim();

        if (pilihan.equals("2") && hero instanceof PemakaiSkill) {
            PemakaiSkill ps = (PemakaiSkill) hero;      // polimorfisme lewat interface
            ps.gunakanSkill(musuh);
        } else if (pilihan.equals("3") && hero instanceof Bertahan) {
            Bertahan b = (Bertahan) hero;
            b.bertahan();
        } else if (pilihan.equals("3") && hero instanceof Penyembuhan) {
            Penyembuhan p = (Penyembuhan) hero;
            p.sembuhkan(cariHpTerendah(party));
        } else {
            hero.serang(musuh);
        }
    }

    private static boolean masihAdaYangHidup(List<Karakter> party) {
        for (Karakter k : party) {
            if (k.isHidup()) {
                return true;
            }
        }
        return false;
    }

    private static Karakter pilihTargetAcak(List<Karakter> party) {
        List<Karakter> hidup = new ArrayList<>();
        for (Karakter k : party) {
            if (k.isHidup()) {
                hidup.add(k);
            }
        }
        return hidup.get(acak.nextInt(hidup.size()));
    }

    private static Karakter cariHpTerendah(List<Karakter> party) {
        Karakter terendah = null;
        for (Karakter k : party) {
            if (k.isHidup() && (terendah == null || k.getHp() < terendah.getHp())) {
                terendah = k;
            }
        }
        return terendah;
    }
}