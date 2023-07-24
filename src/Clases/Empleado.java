package Clases;

import java.sql.Date;
import java.util.Objects;

public class Empleado {
	private int codEmp;
	private int codDpto;
	private int Tlf;
	private Date fechaIngreso;
	private Date fechaNaci;
	private double salario;
	private double comision;
	private int numHijos;
	private String Nombre;
	
	
	
	
	public Empleado(int codEmp, int codDpto, int tlf, Date fechaNaci, Date fechaIngreso, double salario,
			double comision, int numHijos, String nombre) {
		super();
		this.codEmp = codEmp;
		this.codDpto = codDpto;
		Tlf = tlf;
		this.fechaIngreso = fechaIngreso;
		this.fechaNaci = fechaNaci;
		this.salario = salario;
		this.comision = comision;
		this.numHijos = numHijos;
		Nombre = nombre;
	}
	public int getCodEmp() {
		return codEmp;
	}
	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}
	public int getCodDpto() {
		return codDpto;
	}
	public void setCodDpto(int codDpto) {
		this.codDpto = codDpto;
	}
	public int getTlf() {
		return Tlf;
	}
	public void setTlf(int tlf) {
		Tlf = tlf;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaNaci() {
		return fechaNaci;
	}
	public void setFechaNaci(Date fechaNaci) {
		this.fechaNaci = fechaNaci;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public int getNumHijos() {
		return numHijos;
	}
	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	@Override
	public String toString() {
		return "Empleado [codEmp=" + codEmp + ", codDpto=" + codDpto + ", Tlf=" + Tlf + ", fechaIngreso=" + fechaIngreso
				+ ", fechaNaci=" + fechaNaci + ", salario=" + salario + ", comision=" + comision + ", numHijos="
				+ numHijos + ", Nombre=" + Nombre + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(codEmp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return codEmp == other.codEmp;
	}
	
}
