/**
 * Subclass: Penyihir (sihir kuat, bisa memulihkan diri). Mengimplementasikan dua interface.
 */
// [Konsep 10] implements dua interface
public class Penyihir extends Petarung implements Penyerang, Penyembuh {

    private static final int BIAYA_SERANGAN = 12;
    private static final int BIAYA_PULIH = 10;

    private int kekuatanSihir;

    public Penyihir(String nama) {
        super(nama, 95, 40, 10, 3);
        this.kekuatanSihir = 12;
    }

    public Penyihir(String nama, int kekuatanSihir) {
        this(nama);
        this.kekuatanSihir = kekuatanSihir;
    }

    // [Konsep 8] if/else membaca & mengubah state (HP, energi)
    @Override
    public void beraksi(Petarung lawan) {
        if (isHpKritis() && getEnergi() >= BIAYA_PULIH) {
            pulihkanDiri();
        } else if (getEnergi() >= BIAYA_SERANGAN) {
            seranganKhusus(lawan);
        } else {
            System.out.println(getNama() + " memukul dengan tongkat dan memulihkan 8 energi.");
            lawan.terimaDamage(hitungDamage(2, lawan));
            setEnergi(getEnergi() + 8);
        }
    }

    @Override
    public void seranganKhusus(Petarung lawan) {
        setEnergi(getEnergi() - BIAYA_SERANGAN);
        System.out.println(getNama() + " merapal " + getNamaSerangan() + "!");
        lawan.terimaDamage(hitungDamage(kekuatanSihir * 2, lawan));
    }

    @Override
    public String getNamaSerangan() {
        return "Bola Api";
    }

    @Override
    public void pulihkanDiri() {
        setEnergi(getEnergi() - BIAYA_PULIH);
        int jumlah = kekuatanSihir * 3;
        setHp(getHp() + jumlah);
        System.out.println(getNama() + " memulihkan diri +" + jumlah + " HP (HP " + getHp() + "/" + getMaxHp() + ")");
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