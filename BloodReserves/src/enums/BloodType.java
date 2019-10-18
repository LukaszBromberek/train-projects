package enums;

public enum BloodType {
	ZERO_RH_N ("0 Rh-"),
	ZERO_RH_P ("0 Rh+"),
	A_RH_N ("A Rh-"),
	A_RH_P ("A Rh+"),
	B_RH_N ("B Rh-"),
	B_RH_P ("B Rh+"),
	AB_RH_N ("AB Rh-"),
	AB_RH_P ("AB Rh+");
	
	private String bloodType;
	
	BloodType(String name) {
		this.bloodType=name;
	}

	public String getBloodType() {
		return bloodType;
	}
}
