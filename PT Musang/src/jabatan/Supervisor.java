package jabatan;

public class Supervisor extends Karyawan {
	private int gaji;

	public Supervisor(String kodeKaryawan, String nama, String jenisKelamin, String jabatan, int gaji) {
		super(kodeKaryawan, nama, jenisKelamin, jabatan);
		this.gaji = gaji;
	}

	
	public int getGaji() {
		return gaji;
	}

	public void setGaji(int gaji) {
		this.gaji = gaji;
	}


	@Override
	public
	int hitungGaji(double bonus) {
		return (int) (gaji + (bonus / 100));
	}

	
}
