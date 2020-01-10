package com.mmm.weixin.dto;


import com.github.pagehelper.PageInfo;

public class AppointmentListDto {

	private PageInfo<AppointmentDto> appointments;
	public PageInfo<AppointmentDto> getAppointments() {
		return appointments;
	}
	public void setAppointments(PageInfo<AppointmentDto> appointments) {
		this.appointments = appointments;
	}
	
	
}
