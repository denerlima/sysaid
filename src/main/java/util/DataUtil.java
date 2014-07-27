package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {
	
	public final static String DIA_FORMAT = "dd";
	public final static String MES_FORMAT = "MM";
	public final static String ANO_FORMAT = "yy";
	public final static String DATE_FORMAT = DIA_FORMAT + "/" + MES_FORMAT + "/" + ANO_FORMAT;
	public final static String TIME_FORMAT = "HH:mm";
	public final static String HOUR_MINUTE_SECOND_FORMAT = "HH:mm:ss";
	public final static String DATE_TIME_FORMAT = DATE_FORMAT + " "	+ TIME_FORMAT;
	public final static String DATE_HOUR_MINUTE_SECOND_FORMAT = DATE_FORMAT + " " + HOUR_MINUTE_SECOND_FORMAT;
	public final static String DATE_TIME_NDS_FORMAT = "yyyyMMddhhmmss";

	public static Date converterStringParaDate(String date) throws Exception {
		if (date != null && date != "") {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			return sdf.parse(date);
		}
		return null;
	}

	public static String converterDateParaString(Date date){
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			return sdf.format(date);
		}
		return "";
	}

	public static String converterDateParaStringDataHora(Date date)
			throws Exception {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
			return sdf.format(date);
		}
		return "";
	}
	
	public static String converterDateParaStringDataHoraMinutosSegundos(Date date)
			throws Exception {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_HOUR_MINUTE_SECOND_FORMAT);
			return sdf.format(date);
		}
		return "";
	}

	public static Date converterStringParaDateHora(String date)
			throws Exception {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
			return sdf.parse(date);
		}
		return null;
	}

	public static String convertDateToStringHora(Date date) throws Exception {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
			return sdf.format(date);
		}
		return "";
	}

	public static java.sql.Date convertDateUtilToDateSql(java.util.Date data) {
		if (data != null) {
			return new java.sql.Date(data.getTime());
		}
		return null;
	}

	public static java.sql.Date convertStringDataToDateSql(String data)
			throws ParseException {
		if (data != null && !data.equals(""))
			return new java.sql.Date(converterStringDataParaDateUtil(data)
					.getTime());
		return null;
	}

	public static java.util.Date converterStringDataParaDateUtil(String data)
			throws ParseException {
		if (data != null && !data.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			return sdf.parse(data);
		} else {
			return null;
		}
	}

	public static String formataAno(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(ANO_FORMAT);
			return sdf.format(data);
		}
		return "";
	}

	public static String formataDia(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(DIA_FORMAT);
			return sdf.format(data);
		}
		return "";
	}

	public static String formataMes(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(MES_FORMAT);
			return sdf.format(data);
		}
		return "";
	}

	public static String formataHora(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(TIME_FORMAT);
			return sdf.format(data);
		}
		return "";
	}

	public static String formataData(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern(DATE_FORMAT);
			return sdf.format(data);
		}
		return "";
	}
	
	public static String retornaDataDiaAnterior() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		return sdf.format(calendar.getTime());
	}

	public static String recuperarStringDataAcrecidaEmDias(Date data,
			int numeroDiasAcrescimo) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ numeroDiasAcrescimo);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		return sdf.format(calendar.getTime());
	}

	public static String recuperarStringDataAcrecidaEmDias(String data,
			int numeroDiasAcrescimo) throws Exception {
		return recuperarStringDataAcrecidaEmDias(converterStringParaDate(data),
				numeroDiasAcrescimo);
	}

	public static Date recuperarDataAcrescidaEmDias(Date data, int numeroDiasAcrescimo) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ numeroDiasAcrescimo);
		return calendar.getTime();
	}
	
	public static Date recuperarDataAcrescidaEmMeses(Date data,
			int numeroMesesAcrescimo) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)
				+ numeroMesesAcrescimo);
		return calendar.getTime();
	}

	public static String recuperarStringDataUltimoDiaMes(String data)
			throws Exception {
		int mes = Integer.parseInt(data.substring(3, 5));
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(converterStringParaDate(data));
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		calendar.set(calendar.get(Calendar.YEAR), mes, 0);
		return sdf.format(calendar.getTime());
	}

	public static String adicionarDiasADataAtual(int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ quantidadeDeDias);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		return sdf.format(calendar.getTime());
	}

	public static String adicionarDiasADataAtual(Calendar data,
			int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		if (null != data) {
			calendar = new GregorianCalendar(data.get(Calendar.YEAR), data
					.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.get(Calendar.DAY_OF_MONTH)
					+ quantidadeDeDias);
		}
		return sdf.format(calendar.getTime());
	}

	public static void adicionarDiasAData(Calendar data, int quantidadeDeDias) {
		if (null != data) {
			data.add(Calendar.DAY_OF_MONTH, +quantidadeDeDias);
		}
	}

	public static void adicionarDiasAData(Date data, int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		adicionarDiasAData(calendar, quantidadeDeDias);
		data.setTime(calendar.getTime().getTime());
	}

	/**
	 * Subtrai uma determinada quantidade de dias à data atual. Retorna a data
	 * formatada de acordo com o Pattern estabelecido em DataUtil.DATE_FORMAT.
	 * 
	 * @param int quantidadeDeDias
	 * @return String
	 */
	public static String subtrairDiasADataAtual(int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				- quantidadeDeDias);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		return sdf.format(calendar.getTime());
	}

	/**
	 * Subtrai uma determinada quantidade de dias à data atual. Retorna a data subraida.
	 * @param quantidadeDeDias
	 * @return
	 */
	public static Date subtrairDiasAData(int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - quantidadeDeDias);
		return calendar.getTime();
	}
	
	/**
	 * Subtrai uma determinada quantidade de dias à uma data passada como
	 * parâmetro. Retorna a data formatada de acordo com o Pattern estabelecido
	 * em DataUtil.DATE_FORMAT.
	 * 
	 * @param Calendar
	 *            data
	 * @param int quantidadeDeDias
	 * @return String
	 */
	public static String subtrairDiasADataAtual(Calendar data,
			int quantidadeDeDias) {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_FORMAT);
		if (null != data) {
			calendar = new GregorianCalendar(data.get(Calendar.YEAR), data
					.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.get(Calendar.DAY_OF_MONTH)
					- quantidadeDeDias);
		}
		return sdf.format(calendar.getTime());
	}



	public static String recuperarDataAtualFormatadaComMilisegundos() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.YEAR) + " - "
				+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND) + ":"
				+ calendar.get(Calendar.MILLISECOND);
	}

	public static Date adicionarMinutosAHora(Date data, int quantidadeDeMinutos) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		adicionarMinutosAHora(calendar, quantidadeDeMinutos);
		data.setTime(calendar.getTime().getTime());
		return data;
	}

	public static void adicionarMinutosAHora(Calendar data,	int quantidadeDeMinutos) {
		if (null != data) {
			data.add(Calendar.MINUTE, quantidadeDeMinutos);
		}
	}

	public static boolean data1MenorQueData2(Date data1, Date data2) {
		Date dat1 = data1;
		Date dat2 = data2;
		try{
			dat1 = converterStringParaDate(formataData(data1));
			dat2 = converterStringParaDate(formataData(data2));
		}catch(Exception e){
			e.printStackTrace();
		}
		/*GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat1);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc.setTime(dat2);
		return gc.before(gc2);*/
		return dat1.before(dat2);
		
	}

	
	
	public static boolean compararDatasIguais(Date data1, Date data2) {
		if(data1 == null || data2 == null) {
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data1);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(data2);
		return gc.compareTo(gc2) == 0;
	}

	public static String retornaHorasEntreDatas(Date inicio, Date fim) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(inicio);
		fin.setTime(fim);
		Long horaInicio = ini.getTimeInMillis();
		Long horaFim = fin.getTimeInMillis();
		Long result = ((horaFim - horaInicio) / (60 * 60 * 1000));
		String resultado;
		if (result > 1) {
			resultado = result.toString() + " hs";
		} else if (result == 1) {
			resultado = result.toString() + " h";
		} else {
			result = ((horaFim - horaInicio) / (60 * 1000));
			resultado = result.toString() + " min";
		}
		return resultado;
	}




	
	public static Date zerarHorarioData(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date ultimoHorarioData(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MILLISECOND, 59);
		return cal.getTime();
	}	

	public static int retornaDiasEntreDatas(Date inicio, Date fim) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(inicio);
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 0);
		fin.setTime(fim);
		fin.set(Calendar.HOUR_OF_DAY, 0);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);
		fin.set(Calendar.MILLISECOND, 0);
		Long horaInicio = ini.getTimeInMillis();
		Long horaFim = fin.getTimeInMillis();
		int resultado = (int) (((horaFim - horaInicio) / (24 * 60 * 60 * 1000)));
		return resultado;
	}
	

	
	/**
	 * Retorna a quantidade de dias entre duas datas.
	 * @param inicio
	 * @param fim
	 * @return int
	 */
	public static int calculaDiasEntreDatas(Date inicio, Date fim) {
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		ini.setTime(inicio);
		ini.set(Calendar.HOUR_OF_DAY, 0);
		ini.set(Calendar.MINUTE, 0);
		ini.set(Calendar.SECOND, 0);
		ini.set(Calendar.MILLISECOND, 0);
		fin.setTime(fim);
		fin.set(Calendar.HOUR_OF_DAY, 0);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);
		fin.set(Calendar.MILLISECOND, 0);
		Long horaInicio = ini.getTimeInMillis();
		Long horaFim = fin.getTimeInMillis();
		return (int) (((horaFim - horaInicio) / (24 * 60 * 60 * 1000)));		
	}
	
	
	public static boolean comparaDataMaior(Date d1, Date d2) {
		int result = d1.compareTo(d2);
		boolean resultado = false;
		if (result <= 0) {
			resultado = true;
		}
		return resultado;
	}	
	
}
