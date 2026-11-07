/**
 * Superclass abstrak untuk seluruh karakter dalam game (pahlawan maupun monster).
 */
public abstract class Karakter {

    // [Konsep 2] Konstanta global (public static final)
    public static final int PERSEN_HP_KRITIS = 30;
    public static final int DAMAGE_MINIMAL = 1;

    private static int jumlahKarakter = 0;

    // [Konsep 2] Atribut instans kekal (private final)
    private final String id;

    // [Konsep 1] State berupa variabel (semua private -> Konsep 3)
    private String nama;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int serang;
    private int pertahanan;

    // [Konsep 3] Overloaded constructor #1 (lengkap)
    public Karakter(String nama, int maxHp, int maxMp, int serang, int pertahanan) {
        jumlahKarakter++;
        this.id = String.format("CHR-%03d", jumlahKarakter);
        this.nama = nama;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMp = maxMp;
        this.mp = maxMp;
        this.serang = serang;
        this.pertahanan = pertahanan;
    }

    // [Konsep 3] Overloaded constructor #2 (nilai default)
    public Karakter(String nama) {
        this(nama, 100, 30, 10, 5);
    }

    // ===== [Konsep 1] Method prosedur (void) =====
    public void terimaDamage(int damage) {
        setHp(this.hp - damage);
        System.out.println("   " + nama + " menerima " + damage + " damage! (HP: " + hp + "/" + maxHp + ")");
        if (!isHidup()) {
            System.out.println("   >>> " + nama + " telah gugur!");
        }
    }

    public void pulihkanHp(int jumlah) {
        setHp(this.hp + jumlah);
        System.out.println("   " + nama + " pulih " + jumlah + " HP! (HP: " + hp + "/" + maxHp + ")");
    }

    public void tampilkanStatus() {
        System.out.println("[" + id + "] " + nama
                + " | HP " + hp + "/" + maxHp
                + " | MP " + mp + "/" + maxMp
                + " | ATK " + serang
                + " | DEF " + pertahanan);
    }

    // ===== [Konsep 1] Method fungsi (return value) =====
    public boolean isHidup() {
        return hp > 0;
    }

    public boolean isHpKritis() {
        return (hp * 100 / maxHp) <= PERSEN_HP_KRITIS;
    }

    public int hitungDamage(int kekuatanSerangan, Karakter target) {
        int damage = kekuatanSerangan + this.serang - target.getPertahanan();
        return Math.max(DAMAGE_MINIMAL, damage);
    }

    // [Konsep 7] Abstract method
    public abstract void serang(Karakter target);

    // ===== [Konsep 3] Getter & Setter =====
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

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = Math.max(0, Math.min(mp, this.maxMp));
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
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