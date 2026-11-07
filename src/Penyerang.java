/**
 * Interface untuk petarung yang punya serangan khusus.
 */
public interface Penyerang {
    void seranganKhusus(Petarung lawan);

    String getNamaSerangan();
}