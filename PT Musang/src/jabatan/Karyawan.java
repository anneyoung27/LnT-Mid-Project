package jabatan;

public abstract class Karyawan {
	private String kodeKaryawan;
	private String nama;
	private String jenisKelamin;
	private String jabatan;

	
	public Karyawan(String kodeKaryawan, String nama, String jenisKelamin, String jabatan) {
		super();
		this.kodeKaryawan = kodeKaryawan;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.jabatan = jabatan;
	
	}

	public String getKodeKaryawan() {
		return kodeKaryawan;
	}

	public void setKodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan = kodeKaryawan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public abstract int hitungGaji(double bonus);
}
