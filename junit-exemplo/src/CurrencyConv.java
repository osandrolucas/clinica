
public class CurrencyConv {
	//Cotação em 30/09/2019
	public static final double DOLAR_TO_REAL = 4.16;
	
	public double dolarToReal(double dolares) {
		return dolares * DOLAR_TO_REAL;
	}
	
	public double realToDolar(double reais) {
		return reais / DOLAR_TO_REAL;
	}
}
