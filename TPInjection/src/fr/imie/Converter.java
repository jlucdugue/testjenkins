package fr.imie;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Convertor
 */
@WebServlet("/Converter")
public class Converter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	@Named("EtoD")
	ICurrencyConverter converterEtoD;

	@Inject
	@Named("DtoE")
	ICurrencyConverter converterDtoE;
	
	@Inject
	IHistoric historic;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Converter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("histo",historic.getHistos());
		request.getRequestDispatcher("/WEB-INF/converter.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		String initialEString = request.getParameter("initialE");
		String initialDString = request.getParameter("initialD");
		BigDecimal initialEValue;
		BigDecimal initialDValue;
		BigDecimal finalDValue = null;
		BigDecimal finalEValue = null;
		try {
			if (!initialEString.isEmpty()) {
				initialEValue = (BigDecimal) decimalFormat
						.parse(initialEString);
				finalDValue = converterEtoD.convert(initialEValue);
				historic.addHisto(initialEValue, finalDValue);
			}
			if (!initialDString.isEmpty()) {
				initialDValue = (BigDecimal) decimalFormat
						.parse(initialDString);
				finalEValue = converterDtoE.convert(initialDValue);
				historic.addHisto(initialDValue, finalEValue);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("finalE", finalEValue);
		request.setAttribute("finalD", finalDValue);
		request.setAttribute("histo",historic.getHistos());

		request.getRequestDispatcher("/WEB-INF/converter.jsp").forward(request,
				response);

	}

}
