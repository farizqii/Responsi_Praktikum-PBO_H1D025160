/**
 * Subclass: Ksatria (tank/fisik). Mengimplementasikan dua interface sekaligus.
 */
// [Konsep 4] extends  |  [Konsep 10] implements dua interface
public class Ksatria extends Karakter implements PemakaiSkill, Bertahan {

    private static final int BIAYA_SKILL = 10;

    private int kekuatanPedang;
    private boolean sedangBertahan;

    public Ksatria(String nama) {
        super(nama, 140, 30, 18, 10); // [Konsep 5] super(...)
        this.kekuatanPedang = 5;
        this.sedangBertahan = false;
    }

    public Ksatria(String nama, int kekuatanPedang) {
        this(nama);
        this.kekuatanPedang = kekuatanPedang;
    }

    // [Konsep 8] if/else membaca & mengubah state (MP)
    @Override
    public void serang(Karakter target) {
        if (getMp() >= BIAYA_SKILL) {
            gunakanSkill(target);
        } else {
            int damage = hitungDamage(kekuatanPedang, target);
            System.out.println(getNama() + " menebas biasa ke " + target.getNama() + " dan memulihkan 5 MP.");
            target.terimaDamage(damage);
            setMp(getMp() + 5);
        }
    }

    @Override
    public void gunakanSkill(Karakter target) {
        if (getMp() >= BIAYA_SKILL) {
            setMp(getMp() - BIAYA_SKILL);
            int damage = hitungDamage(kekuatanPedang * 3, target);
            System.out.println(getNama() + " memakai skill " + getNamaSkill() + " ke " + target.getNama() + "!");
            target.terimaDamage(damage);
        } else {
            System.out.println(getNama() + " kekurangan MP untuk " + getNamaSkill() + "!");
        }
    }

    @Override
    public String getNamaSkill() {
        return "Tebasan Kuat";
    }

    @Override
    public void bertahan() {
        this.sedangBertahan = true;
        System.out.println(getNama() + " mengangkat perisai! Damage berikutnya berkurang 50%.");
    }

    // [Konsep 6] Method overriding (+ [Konsep 5] super.namaMethod())
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
        System.out.println("      Kelas: Ksatria | Kekuatan Pedang: " + kekuatanPedang
                + " | Bertahan: " + (sedangBertahan ? "Ya" : "Tidak"));
    }

    public int getKekuatanPedang() {
        return kekuatanPedang;
    }

    public void setKekuatanPedang(int kekuatanPedang) {
        this.kekuatanPedang = kekuatanPedang;
    }

    public boolean isSedangBertahan() {
        return sedangBertahan;
    }

    public void setSedangBertahan(boolean sedangBertahan) {
        this.sedangBertahan = sedangBertahan;
    }
}