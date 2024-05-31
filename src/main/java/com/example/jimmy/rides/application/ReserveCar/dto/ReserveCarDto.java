package com.example.jimmy.rides.application.ReserveCar.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReserveCarDto {
	@Schema(example="05-31-2024 11:30")
	public String startDate;
	@Schema(example="1")
	public Integer duration;
  
  public String getStartDate(){
      return this.startDate;
  }

  public Integer getDuration(){
      return this.duration;
  }

  public void setStartDate(String date){
       this.startDate = date;
  }

  public void setDuration(Integer duration){
      this.duration = duration;
  }

}
