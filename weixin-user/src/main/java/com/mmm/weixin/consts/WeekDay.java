package com.mmm.weixin.consts;
public enum WeekDay{
    SUNDAY(1, "周日"),
    MONDAY(2, "周一"),
    TUESDAY(3, "周二"),
	WEDNESDAY(4, "周三"),
	THURSDAY(5, "周四"),
	FRIDAY(6, "周五"),
	SATURDAY(7, "周六");

    private Integer type;
    private String description;

    private WeekDay(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
    
    

    public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public static WeekDay typeOf(Integer value) {

        switch (value) {
            case 1: {
                return SUNDAY;
            }
            case 2: {
                return MONDAY;
            }
            case 3: {
                return TUESDAY;
            }
            case 4: {
            	return WEDNESDAY;
            }
            case 5: {
            	return THURSDAY;
            }
            case 6: {
            	return FRIDAY;
            }
            case 7: {
            	return SATURDAY;
            }
            default: {
                return null;
            }
        }
    }
	
}
