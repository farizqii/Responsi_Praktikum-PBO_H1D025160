/**
 * Subclass: Gladiator (petarung fisik). Mengimplementasikan dua interface.
 */
// [Konsep 4] extends | [Konsep 10] implements dua interface
public class Gladiator extends Petarung implements Penyerang, Bertahan {

    private static final int BIAYA_SERANGAN = 10;

    private boolean sedangBertahan;

    public Gladiator(String nama) {
        super(nama, 130, 20, 15, 8); // [Konsep 5] super(...)
        this.sedangBertahan = false;
    }

    public Gladiator(String nama, int serang) {
        this(nama);
        setSerang(serang);
    }

    // [Konsep 8] if/else membaca & mengubah state (energi, HP)
    @Override
    public void beraksi(Petarung lawan) {
        if (getEnergi() >= BIAYA_SERANGAN) {
            seranganKhusus(lawan);
        } else if (isHpKritis() && !sedangBertahan) {
            bertahan();
        } else {
            System.out.println(getNama() + " menebas biasa dan memulihkan 5 energi.");
            lawan.terimaDamage(hitungDamage(3, lawan));
            setEnergi(getEnergi() + 5);
        }
    }

    @Override
    public void seranganKhusus(Petarung lawan) {
        setEnergi(getEnergi() - BIAYA_SERANGAN);
        System.out.println(getNama() + " memakai " + getNamaSerangan() + "!");
        lawan.terimaDamage(hitungDamage(15, lawan));
    }

    @Override
    public String getNamaSerangan() {
        return "Tebasan Pedang";
    }

    @Override
    public void bertahan() {
        this.sedangBertahan = true;
        System.out.println(getNama() + " mengangkat perisai! Damage berikutnya berkurang 50%.");
    }

    // [Konsep 6] Overriding + [Konsep 5] super.namaMethod()
    @Override
    public void terimaDamage(int damage) {
        if (sedangBertahan) {
            damage = damage / 2;
            sedangBertahan = false;
        }
        super.terimaDamage(damage);
    }

    @Override
    public void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("      Kelas: Gladiator | Bertahan: " + (sedangBertahan ? "Ya" : "Tidak"));
    }

    public boolean isSedangBertahan() {
        return sedangBertahan;
    }

    public void setSedangBertahan(boolean sedangBertahan) {
        this.sedangBertahan = sedangBertahan;
    }
}