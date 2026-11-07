/**
 * Class Main (terpisah dari class lain) - [Konsep 4].
 * Bagian 1: pembuktian 10 persyaratan. Bagian 2: duel otomatis di arena.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==== ARENA DUEL - H1D025160 ====");
        buktikanPersyaratan();
        mulaiDuel();
    }

    private static void buktikanPersyaratan() {
        Gladiator gladiator = new Gladiator("Maximus");
        Penyihir penyihir = new Penyihir("Merlin");

        System.out.println("\n--- BAGIAN 1: PEMBUKTIAN 10 PERSYARATAN ---");

        System.out.println("\n[1] State & Behavior");
        gladiator.tampilkanStatus();                                    // void
        System.out.println("hitungDamage Maximus -> Merlin = " + gladiator.hitungDamage(10, penyihir)); // return

        System.out.println("\n[2] Immutability");
        System.out.println("Konstanta PERSEN_HP_KRITIS = " + Petarung.PERSEN_HP_KRITIS);
        System.out.println("ID final (tanpa setter) = " + gladiator.getId());

        System.out.println("\n[3] Enkapsulasi & Overloading");
        Gladiator kuat = new Gladiator("Spartacus", 25);                // constructor 2 parameter
        Gladiator biasa = new Gladiator("Crixus");                      // constructor 1 parameter
        System.out.println(kuat.getNama() + " ATK " + kuat.getSerang() + " | " + biasa.getNama() + " ATK " + biasa.getSerang());

        System.out.println("\n[4] Inheritance");
        System.out.println("Gladiator & Penyihir extends Petarung: " + (gladiator instanceof Petarung));

        System.out.println("\n[5] Super Chaining");
        System.out.println("super(...) di konstruktor; super.tampilkanStatus() di override:");
        penyihir.tampilkanStatus();

        System.out.println("\n[6] Method Overriding");
        gladiator.bertahan();
        gladiator.terimaDamage(40);                                     // override terimaDamage -> dikurangi 50%

        System.out.println("\n[7] Abstract Class");
        System.out.println("Petarung adalah abstract class dengan abstract method beraksi(Petarung).");

        System.out.println("\n[8] Logika if/else pada state");
        Penyihir uji = new Penyihir("Penyihir Uji");
        Gladiator sasaran = new Gladiator("Boneka Latihan");
        for (int i = 1; i <= 4; i++) {
            System.out.println("Aksi " + i + " (energi " + uji.getEnergi() + ")");
            uji.beraksi(sasaran);
        }

        System.out.println("\n[9] Polimorfisme Interface");
        Penyerang p1 = new Gladiator("Maximus");
        Penyerang p2 = new Penyihir("Merlin");
        Gladiator target = new Gladiator("Target");
        p1.seranganKhusus(target);
        p2.seranganKhusus(target);

        System.out.println("\n[10] Multiple Interface");
        System.out.println("Gladiator implements Penyerang, Bertahan");
        System.out.println("Penyihir implements Penyerang, Penyembuh");
        Penyembuh penyembuh = new Penyihir("Healer");
        penyembuh.pulihkanDiri();
    }

    private static void mulaiDuel() {
        Gladiator gladiator = new Gladiator("Maximus");
        Penyihir penyihir = new Penyihir("Merlin");

        System.out.println("\n--- BAGIAN 2: DUEL DI ARENA ---");
        gladiator.tampilkanStatus();
        penyihir.tampilkanStatus();

        int ronde = 1;
        while (gladiator.isHidup() && penyihir.isHidup() && ronde <= 50) {
            System.out.println("\n== Ronde " + ronde + " ==");
            gladiator.beraksi(penyihir);
            if (penyihir.isHidup()) {
                penyihir.beraksi(gladiator);
            }
            ronde++;
        }

        System.out.println("\n--- HASIL ---");
        if (gladiator.isHidup() && !penyihir.isHidup()) {
            System.out.println(gladiator.getNama() + " memenangkan duel!");
        } else if (penyihir.isHidup() && !gladiator.isHidup()) {
            System.out.println(penyihir.getNama() + " memenangkan duel!");
        } else {
            System.out.println("Duel berakhir seri.");
        }
        gladiator.tampilkanStatus();
        penyihir.tampilkanStatus();
    }
}