package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  // E Static para não ser instaciado outro na classe
	
	// Metodo construtor
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new  DomainException("Error in reservation: Check-out date must be after check-in date!!");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	// Metodos Gets e Sets
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}


	public Date getCheckIn() {
		return checkIn;
	}


	public Date getCheckOut() {
		return checkOut;
	}
	
	// Set CheckIn e o Set CheckOut não existem porque as datas serão mudadas por um metodo personalizado.
	
	public long duration() {  // long e um tipo inteiro maior
		long diff = checkOut.getTime() - checkIn.getTime();  // O getTime pega a data em millisegundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);   // Converte a variavel diff de millisegundos para dias.
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		// Checa se as datas para atualização não serem anterior a atual.
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Erro in reservation: Reservation dates for update must be future dates!!");
		} 
		if (!checkOut.after(checkIn)) {
			throw new  DomainException("Error in reservation: Check-out date must be after check-in date!!");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut) + ", " + duration() + " nights.";
	}
}

