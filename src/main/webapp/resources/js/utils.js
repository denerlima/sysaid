
function soBigDecimal(campo) {
	valor = campo.value;
	tam = valor.length;
	str = "";
	var i = tam-1;
	//for (i = 0; i < tam; i++) {
		carac = valor.charAt(i);
		if (carac == "0" || carac == "1" || carac == "2" || carac == "3"
				|| carac == "4" || carac == "5" || carac == "6" || carac == "7"
				|| carac == "8" || carac == "9" || carac == "," || carac == ".") {
			str = str + carac;
		}
	//}
	campo.value = str;
	return campo;
}

function soBigDecimalSimples(campo) {
	valor = campo.value;
	tam = valor.length;
	str = "";
	for (i = 0; i < tam; i++) {
		carac = valor.charAt(i);
		if (carac == "0" || carac == "1" || carac == "2" || carac == "3"
				|| carac == "4" || carac == "5" || carac == "6" || carac == "7"
				|| carac == "8" || carac == "9" || carac == ",") {
			str = str + carac;
		}
	}
	campo.value = str;
	return campo;
}
