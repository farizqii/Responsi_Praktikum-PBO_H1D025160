/**
 * Subclass: Monster (musuh). Mengamuk saat HP kritis.
 */
public class Monster extends Karakter implements PemakaiSkill {

    private String jenis;
    private boolean mengamuk;

    public Monster(String nama, String jenis, int maxHp, int serang, int pertahanan) {
        super(nama, maxHp, 0, serang, pertahanan);
        this.jenis = jenis;
        this.mengamuk = false;
    }

    public Monster(String nama) {
        this(nama, "Goblin", 120, 14, 4);
    }

    // [Konsep 8] if/else membaca & mengubah state (HP kritis -> ATK naik)
    @Override
    public void serang(Karakter target) {
        if (isHpKritis() && !mengamuk) {
            this.mengamuk = true;
            setSerang(getSerang() + 8);
            System.out.println("!!! " + getNama() + " MENGAMUK! Serangannya meningkat!");
            gunakanSkill(target);
        } else if (mengamuk) {
            gunakanSkill(target);
        } else {
            int damage = hitungDamage(10, target);
            System.out.println(getNama() + " mencakar " + target.getNama() + "!");
            target.terimaDamage(damage);
        }
    }

    @Override
    public void gunakanSkill(Karakter target) {
        int damage = hitungDamage(15, target);
        System.out.println(getNama() + " menggunakan " + getNamaSkill() + " ke " + target.getNama() + "!");
        target.terimaDamage(damage);
    }

    @Override
    public String getNamaSkill() {
        return mengamuk ? "Amukan Brutal" : "Hantaman Berat";
    }

    @Override
    public void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("      Jenis: " + jenis + " | Mengamuk: " + (mengamuk ? "Ya" : "Tidak"));
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public boolean isMengamuk() {
        return mengamuk;
    }

    public void setMengamuk(boolean mengamuk) {
        this.mengamuk = mengamuk;
    }
}