/**
 * Interface untuk semua karakter yang memiliki skill khusus.
 */
public interface PemakaiSkill {
    void gunakanSkill(Karakter target);
    String getNamaSkill();
}
