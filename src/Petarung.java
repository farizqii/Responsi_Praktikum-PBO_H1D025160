/**
 * Superclass abstrak untuk semua petarung di arena.
 */
public abstract class Petarung {

    // [Konsep 2] Konstanta global
    public static final int PERSEN_HP_KRITIS = 30;
    public static final int DAMAGE_MINIMAL = 1;

    private static int jumlahPetarung = 0;

    // [Konsep 2] Atribut instans kekal
    private final String id;

    // [Konsep 1] State (semua private -> Konsep 3)
    private String nama;
    private int hp;
    private int maxHp;
    private int energi;
    private int serang;
    private int pertahanan;

    // [Konsep 3] Constructor overload #1
    public Petarung(String nama, int maxHp, int energi, int serang, int pertahanan) {
        jumlahPetarung++;
        this.id = "PTR-" + jumlahPetarung;
        this.nama = nama;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.energi = energi;
        this.serang = serang;
        this.pertahanan = pertahanan;
    }

    // [Konsep 3] Constructor overload #2
    public Petarung(String nama) {
        this(nama, 100, 20, 10, 5);
    }

    // [Konsep 1] Method prosedur (void)
    public void terimaDamage(int damage) {
        setHp(this.hp - damage);
        System.out.println("   " + nama + " menerima " + damage + " damage (HP " + hp + "/" + maxHp + ")");
    }

    public void tampilkanStatus() {
        System.out.println("[" + id + "] " + nama + " | HP " + hp + "/" + maxHp
                + " | Energi " + energi + " | ATK " + serang + " | DEF " + pertahanan);
    }

    // [Konsep 1] Method fungsi (return value)
    public boolean isHidup() {
        return hp > 0;
    }

    public boolean isHpKritis() {
        return (hp * 100 / maxHp) <= PERSEN_HP_KRITIS;
    }

    public int hitungDamage(int kekuatan, Petarung lawan) {
        return Math.max(DAMAGE_MINIMAL, kekuatan + serang - lawan.getPertahanan());
    }

    // [Konsep 7] Abstract method
    public abstract void beraksi(Petarung lawan);

    // [Konsep 3] Getter & Setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, this.maxHp));
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getEnergi() {
        return energi;
    }

    public void setEnergi(int energi) {
        this.energi = Math.max(0, energi);
    }

    public int getSerang() {
        return serang;
    }

    public void setSerang(int serang) {
        this.serang = serang;
    }

    public int getPertahanan() {
        return pertahanan;
    }

    public void setPertahanan(int pertahanan) {
        this.pertahanan = pertahanan;
    }
}