/**
 * Subclass: Pendeta (support/healer). Mengimplementasikan dua interface sekaligus.
 */
// [Konsep 10] implements dua interface
public class Pendeta extends Karakter implements PemakaiSkill, Penyembuhan {

    private static final int BIAYA_SKILL = 10;
    private static final int BIAYA_HEAL = 12;

    private int kekuatanSuci;

    public Pendeta(String nama) {
        super(nama, 100, 50, 9, 5);
        this.kekuatanSuci = 8;
    }

    public Pendeta(String nama, int kekuatanSuci) {
        this(nama);
        this.kekuatanSuci = kekuatanSuci;
    }

    // [Konsep 8] if/else membaca & mengubah state (HP dan MP)
    @Override
    public void serang(Karakter target) {
        if (getHp() < getMaxHp() / 2 && getMp() >= BIAYA_HEAL) {
            System.out.println(getNama() + " HP-nya rendah, memilih menyembuhkan diri sendiri.");
            sembuhkan(this);
        } else if (getMp() >= BIAYA_SKILL) {
            gunakanSkill(target);
        } else {
            int damage = hitungDamage(1, target);
            System.out.println(getNama() + " memukul dengan tongkat suci ke " + target.getNama() + ".");
            target.terimaDamage(damage);
            setMp(getMp() + 4);
        }
    }

    @Override
    public void gunakanSkill(Karakter target) {
        if (getMp() >= BIAYA_SKILL) {
            setMp(getMp() - BIAYA_SKILL);
            int damage = hitungDamage(kekuatanSuci * 2, target);
            System.out.println(getNama() + " memanggil " + getNamaSkill() + " ke " + target.getNama() + "!");
            target.terimaDamage(damage);
        } else {
            System.out.println(getNama() + " kekurangan MP untuk " + getNamaSkill() + "!");
        }
    }

    @Override
    public String getNamaSkill() {
        return "Cahaya Suci";
    }

    @Override
    public void sembuhkan(Karakter target) {
        if (getMp() >= BIAYA_HEAL) {
            setMp(getMp() - BIAYA_HEAL);
            System.out.println(getNama() + " menyembuhkan " + target.getNama() + " dengan Doa Pemulihan.");
            target.pulihkanHp(kekuatanSuci * 4);
        } else {
            System.out.println(getNama() + " kekurangan MP untuk menyembuhkan!");
        }
    }

    @Override
    public void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("      Kelas: Pendeta | Kekuatan Suci: " + kekuatanSuci);
    }

    public int getKekuatanSuci() {
        return kekuatanSuci;
    }

    public void setKekuatanSuci(int kekuatanSuci) {
        this.kekuatanSuci = kekuatanSuci;
    }
}