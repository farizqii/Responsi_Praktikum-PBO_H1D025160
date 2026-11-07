/**
 * Subclass: Penyihir (damage sihir tinggi, HP rendah).
 */
public class Penyihir extends Karakter implements PemakaiSkill {

    private static final int BIAYA_SKILL = 15;

    private int kekuatanSihir;

    public Penyihir(String nama) {
        super(nama, 80, 60, 8, 3);
        this.kekuatanSihir = 12;
    }

    public Penyihir(String nama, int kekuatanSihir) {
        this(nama);
        this.kekuatanSihir = kekuatanSihir;
    }

    // [Konsep 8] if/else membaca & mengubah state (MP)
    @Override
    public void serang(Karakter target) {
        if (getMp() >= BIAYA_SKILL) {
            gunakanSkill(target);
        } else {
            int damage = hitungDamage(2, target);
            System.out.println(getNama() + " memukul dengan tongkat ke " + target.getNama()
                    + " dan memulihkan 8 MP.");
            target.terimaDamage(damage);
            setMp(getMp() + 8);
        }
    }

    @Override
    public void gunakanSkill(Karakter target) {
        if (getMp() >= BIAYA_SKILL) {
            setMp(getMp() - BIAYA_SKILL);
            int damage = hitungDamage(kekuatanSihir * 3, target);
            System.out.println(getNama() + " merapal " + getNamaSkill() + " ke " + target.getNama() + "!");
            target.terimaDamage(damage);
        } else {
            System.out.println(getNama() + " kekurangan MP untuk " + getNamaSkill() + "!");
        }
    }

    @Override
    public String getNamaSkill() {
        return "Bola Api";
    }

    @Override
    public void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("      Kelas: Penyihir | Kekuatan Sihir: " + kekuatanSihir);
    }

    public int getKekuatanSihir() {
        return kekuatanSihir;
    }

    public void setKekuatanSihir(int kekuatanSihir) {
        this.kekuatanSihir = kekuatanSihir;
    }
}