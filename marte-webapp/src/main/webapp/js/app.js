/**
 * 
 */

$(function() {
	handlerChangeTipoDescuento();
	$("#tipoDescuento").change(handlerChangeTipoDescuento);
	
	if ( ( $("#optionText01").length > 0 ) && ( $("#add-option-01").length > 0 ) ) { addOptionBindings01(); }				
	if ( ( $("#optionText02").length > 0 ) && ( $("#add-option-02").length > 0 ) ) { addOptionBindings02(); }				
	if ( ( $("#optionText03").length > 0 ) && ( $("#add-option-03").length > 0 ) ) { addOptionBindings03(); }				
	if ( ( $("#optionText04").length > 0 ) && ( $("#add-option-04").length > 0 ) ) { addOptionBindings04(); }				
	if ( ( $("#optionText05").length > 0 ) && ( $("#add-option-05").length > 0 ) ) { addOptionBindings05(); }
	
	$(document).on("click", ".show-det", function(event){
		
		event.preventDefault(); 
	    var url = $(this).attr("href");
	    $("#detail-num-comercial").load(url);

	} );
	
	$(".del-acuerdo").confirm({
		title:"Confirmación BORRADO de Acuerdo",
        text: "Esto borrará TODOS los datos del Acuerdo. Estás completamente seguro?",
        confirm: function(event){
        	
        	// en la propiedad 'context' se halla la url de 'a href'
    	    var url = event.context;
    	    
    	    $("#info-acuerdos").hide();
    	    
    	    $("#load-waiting").hide();
    		var p_info = $('<p></p>');
    		p_info.text('Eliminando datos de Acuerdo en el sistema. Esto puede tardar unos minutos...');
    		$('#load-waiting')
    				.empty()
    				.addClass('text-center');
    		$('#load-waiting')
    				.append(p_info);

    	    
    		var cl = new CanvasLoader('load-waiting');
    		cl.setColor('#3f68a6'); // default is '#000000'
    		cl.setShape('roundRect'); // default is 'oval'
    		cl.setDiameter(114); // default is 40
    		cl.setDensity(17); // default is 40
    		cl.setRange(1); // default is 1.3
    		cl.setSpeed(1); // default is 2
    		cl.setFPS(10); // default is 24
    		cl.show(); // Hidden by default
    	    
    	    $("#load-waiting").show();
    	    
    	    window.location.href = url;
    	    
    	},
        cancel: function(button) {
            alert("Operación cancelada");
        },
        confirmButton: "Sí, confirmo",
        cancelButton: "No"		
	});
	
	$('#fecha').datepicker({
		format: "yyyymmdd",
	    weekStart: 1,
	    todayBtn: "linked",
	    clearBtn: true,
	    language: "es",
	    todayHighlight: true
	});
	
    var listaAsignadas = $("#informe-new-sortableA");
    var listaDisponibles = $("#informe-new-sortableD");
	
	
	$( "#informe-new-sortableD, #informe-new-sortableA" ).sortable({
		connectWith: ".connectedSortable"
		, receive: function( event, ui ) {
			    	console.log("listaAsignadas.children().length : " + listaAsignadas.children().length);
		    		if(listaAsignadas.children().length > 1){
		    			listaAsignadas.remove($('#pestanya_0'));
		    		}
		    		if(listaAsignadas.children().length == 0){
		    			var defaultLi = "<a href='#' id='pestanya_0' class='list-group-item'><span class='listItemLabel'>Add tabs here...</span></a>";
		    			listaAsignadas.append(defaultLi);
		    		}
				}
	}).disableSelection();
    
    var informeFormNew = $("#informe-form-new");
    
    var submitInformeForm = function(){
    	var los = $("#informe-new-sortableA").sortable('toArray');
    	$("#ordenadas").val(los);
    	return true;
    } 
    
    informeFormNew.on('submit', submitInformeForm);

    var movePestanyesByButtons = function () {
        var $button = $(this), actives = '';
        if ($button.hasClass('move-left')) {
            actives = $('#informe-new-sortableA a.active');
            actives.clone().appendTo('#informe-new-sortableD').toggleClass('active');
            actives.remove();
        } else if ($button.hasClass('move-right')) {
        	
            actives = $('#informe-new-sortableD a.active');
            actives.clone().appendTo('#informe-new-sortableA').toggleClass('active');
            actives.remove();
        }
        return false;
    }
    
    $('.list-arrows button').on('click', movePestanyesByButtons);

	$('body').on('click', '.list-group .list-group-item', function () {
        $(this).toggleClass('active');
    });
	
	
	var username = $('#username');
	
	username.on('change', getNewPassword);
	
	var acuerdoShowTabs = $('#acuerdo-show-tabs');
	acuerdoShowTabs.tab();
	
	var motivoBajaMARTE = $('#motivoBajaMARTE');
	motivoBajaMARTE.on('change', motivoBajaMARTEOptionsHandler);
	
	$('#tramitacion-baja-cambio-cif').hide();
	
	
	$('#tram-baja-form-btn-back').on('click', function(){
		$('#tram-baja-form-step-2').attr('action', '/marte-webapp/tram/baja/form');
		$('#tram-baja-form-step-2').submit();
	});
	$('#tram-baja-form-btn-confirm').on('click', function(){
		$('#tram-baja-form-step-2').attr('action', '/marte-webapp/tram/baja/ok');
		$('#tram-baja-form-step-2').submit();
	});
	
	$('#tram-otros-form-btn-back').on('click', function(){
		$('#tram-otros-form-step-2').attr('action', '/marte-webapp/tram/otros/form');
		$('#tram-otros-form-step-2').submit();
	});
	$('#tram-otros-form-btn-confirm').on('click', function(){
		$('#tram-otros-form-step-2').attr('action', '/marte-webapp/tram/otros/ok');
		$('#tram-otros-form-step-2').submit();
	});
	
	$('#tram-mod-email-form-btn-back').on('click', function(){
		$('#tram-mod-email-form-step-2').attr('action', '/marte-webapp/tram/modemail/form');
		$('#tram-mod-email-form-step-2').submit();
	});
	$('#tram-mod-email-form-btn-confirm').on('click', function(){
		$('#tram-mod-email-form-step-2').attr('action', '/marte-webapp/tram/modemail/ok');
		$('#tram-mod-email-form-step-2').submit();
	});
	
	$('#tram-mod-ccc-form-btn-back').on('click', function(){
		$('#tram-mod-ccc-form-step-2').attr('action', '/marte-webapp/tram/modccc/form');
		$('#tram-mod-ccc-form-step-2').submit();
	});
	$('#tram-mod-ccc-form-btn-confirm').on('click', function(){
		$('#tram-mod-ccc-form-step-2').attr('action', '/marte-webapp/tram/modccc/ok');
		$('#tram-mod-ccc-form-step-2').submit();
	});
	
	$('#tram-mod-ccc-form-btn-save').on('click', function(event){
		event.preventDefault();
		if(clickBtnCCCValidate()){
			var l = Ladda.create(this);
			l.start();
			$('#tram-mod-ccc-form-step-1').submit();
		}else{
			$('#tram-mod-ccc-form-btn-save').addClass('disabled');
			$('#tram-mod-ccc-form-btn-save').prop('disabled', true);
		}
	});
	
	$('#tram-mod-ccc-form-btn-confirm').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-ccc-form-step-2').submit();
	});
	
	$('#tram-mod-co-form-btn-save').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-co-form-step-1').submit();
	});
	
	$('#tram-mod-co-form-btn-confirm').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-co-form-step-2').submit();
	});
	
	
	$('#tram-mod-pv-form-btn-save').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-pv-form-step-1').submit();
	});
	
	$('#tram-mod-pv-form-btn-confirm').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-pv-form-step-2').submit();
	});
	
	$('#tram-mod-email-form-btn-save').on('click', function(event){
		var valid = $('#tram-mod-pv-form-step-1')[0].checkValidity();
		event.preventDefault();
		if(valid){
			var l = Ladda.create(this);
			l.start();
			$('#tram-mod-email-form-step-1').submit();
		}
	});
	
	$('#tram-mod-dom-form-btn-save').on('click', function(event){
		var valid = $('#tram-mod-dom-form-step-1')[0].checkValidity();
		event.preventDefault();
		if(valid){
			var l = Ladda.create(this);
			l.start();
			$('#tram-mod-dom-form-step-1').submit();
		}
	});
	
	$('#tram-mod-email-form-btn-confirm').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-email-form-step-2').submit();
	});
	
	$('#tram-mod-dom-form-btn-confirm').on('click', function(event){
		event.preventDefault();
		var l = Ladda.create(this);
		l.start();
		$('#tram-mod-dom-form-step-2').submit();
	});
	
	
	//tram-mod-ccc-form-btn-confirm
	
	$('#ccc-valid').on('click', clickBtnCCCValidate);
	
	$('.ccc-copy').on('click', function(){
		var ccc = $(this).attr('id');
		//alert("CCC to copy " + ccc);
		$('#ccc-01').val(ccc.substr(1,4));
		$('#ccc-02').val(ccc.substr(5,4));
		$('#ccc-03').val(ccc.substr(9,2));
		$('#ccc-04').val(ccc.substr(11,10));
		//012345678901234567890
		clickBtnCCCValidate();
	});
	
	$('.co-copy').on('click', function(){
		var ccc = $(this).attr('id');
		//alert("CCC to copy " + ccc);
		$('#co-01').val(ccc.substr(1,4));
		$('#co-02').val(ccc.substr(5,4));
	});
	
	
	
	$('#tram-mod-co-form-btn-back').on('click', function(){
		$('#tram-mod-co-form-step-2').attr('action', '/marte-webapp/tram/modco/form');
		$('#tram-mod-co-form-step-2').submit();
	});
	$('#tram-mod-co-form-btn-confirm').on('click', function(){
		$('#tram-mod-co-form-step-2').attr('action', '/marte-webapp/tram/modco/ok');
		$('#tram-mod-co-form-step-2').submit();
	});
	
	$('#tram-mod-pv-form-btn-back').on('click', function(){
		$('#tram-mod-pv-form-step-2').attr('action', '/marte-webapp/tram/modpv/form');
		$('#tram-mod-pv-form-step-2').submit();
	});
	$('#tram-mod-pv-form-btn-confirm').on('click', function(){
		$('#tram-mod-pv-form-step-2').attr('action', '/marte-webapp/tram/modpv/ok');
		$('#tram-mod-pv-form-step-2').submit();
	});
	
	$('#tram-mod-tiposoporte-form-btn-back').on('click', function(){
		$('#tram-mod-tiposoporte-form-step-2').attr('action', '/marte-webapp/tram/modtiposoporte/form');
		$('#tram-mod-tiposoporte-form-step-2').submit();
	});
	$('#tram-mod-tiposoporte-form-btn-confirm').on('click', function(){
		$('#tram-mod-tiposoporte-form-step-2').attr('action', '/marte-webapp/tram/modtiposoporte/ok');
		$('#tram-mod-tiposoporte-form-step-2').submit();
	});
	
	$('#tram-mod-dom-form-btn-back').on('click', function(){
		$('#tram-mod-dom-form-step-2').attr('action', '/marte-webapp/tram/moddom/form');
		$('#tram-mod-dom-form-step-2').submit();
	});
	$('#tram-mod-dom-form-btn-confirm').on('click', function(){
		$('#tram-mod-dom-form-step-2').attr('action', '/marte-webapp/tram/moddom/ok');
		$('#tram-mod-dom-form-step-2').submit();
	});
	
	
	$('#btn-dom-copy').on('click', function(event){
		$('#dom-fac-aa').val($('#dom-soc-aa').val());
		$('#dom-fac-dir').val($('#dom-soc-dir').val());
		$('#dom-fac-cp').val($('#dom-soc-cp').val());
		$('#dom-fac-localidad').val($('#dom-soc-localidad').val());
		$('#dom-fac-provincia').val($('#dom-soc-provincia').val());
	});
	
});


var motivoBajaMARTEOptionsHandler = function(){
	var motivoBaja = $('#motivoBajaMARTE').val();
	if(motivoBaja == '17' || motivoBaja == '18'){ //Cambio de CIF
		$('#tramitacion-baja-cambio-cif').show();
	}else{
		$('#tramitacion-baja-cambio-cif').hide();
	}
}

var getNewPassword = function (){
	var userName = $('#username').val();
	console.log(userName);
	$.ajax({
		url : '/informes-webapp/usuario/getRandPass?username='+userName,
		success: function(data){
//			var msg = "Tenemos la contraseña!";
//			alert("OK:" + msg +":"+ data);
			$('#password1').val(data);
		},

		error: function(data){
			alert("ERROR:" + data);
		}

	
	});
}

function handlerChangeTipoDescuento() {
	if($("#tipoDescuento").val() == 'PPM'){
		$("#fgPrecioPorMinuto").show();
		$("#fgPorcentajeDescuento").hide();
	}else if($("#tipoDescuento").val() == 'BOE'){
		$("#fgPrecioPorMinuto").hide();
		$("#fgPorcentajeDescuento").hide();
	}else{ // DTO
		$("#fgPrecioPorMinuto").hide();
		$("#fgPorcentajeDescuento").show();
	}
}

// Generate Option
function addOptionBindings01() {

	$('#add-option-01').click(function(){
		createOption01();		
		return false;
	});

	$('#optionText01').keyup(function (e) {  
		if (e.keyCode == 13) {
			createOption01(); 
			}  
	});
}

function createOption01() {
	if ($('#optionText01').val() != '') {
			$('<option>'+ $('#optionText01').val() +'</option>').appendTo("#ceco01");
			$('#optionText01').val('');	
		}	
}

function addOptionBindings02() {
	
	$('#add-option-02').click(function(){
		createOption02();		
		return false;
	});
	
	$('#optionText02').keyup(function (e) {  
		if (e.keyCode == 13) {
			createOption02(); 
		}  
	});
}

function createOption02() {
	if ($('#optionText02').val() != '') {
		$('<option>'+ $('#optionText02').val() +'</option>').appendTo("#ceco02");
		$('#optionText02').val('');	
	}	
}

function addOptionBindings03() {
	
	$('#add-option-03').click(function(){
		createOption03();		
		return false;
	});
	
	$('#optionText03').keyup(function (e) {  
		if (e.keyCode == 13) {
			createOption03(); 
		}  
	});
}

function createOption03() {
	if ($('#optionText03').val() != '') {
		$('<option>'+ $('#optionText03').val() +'</option>').appendTo("#ceco03");
		$('#optionText03').val('');	
	}	
}

function addOptionBindings04() {
	
	$('#add-option-04').click(function(){
		createOption04();		
		return false;
	});
	
	$('#optionText04').keyup(function (e) {  
		if (e.keyCode == 13) {
			createOption04(); 
		}  
	});
}

function createOption04() {
	if ($('#optionText04').val() != '') {
		$('<option>'+ $('#optionText04').val() +'</option>').appendTo("#ceco04");
		$('#optionText04').val('');	
	}	
}

function addOptionBindings05() {
	
	$('#add-option-05').click(function(){
		createOption05();		
		return false;
	});
	
	$('#optionText05').keyup(function (e) {  
		if (e.keyCode == 13) {
			createOption05(); 
		}  
	});
}

function createOption05() {
	if ($('#optionText05').val() != '') {
		$('<option>'+ $('#optionText05').val() +'</option>').appendTo("#ceco05");
		$('#optionText05').val('');	
	}	
}

function showDetails(){
// info-num-comercial	
    var url = $(".show-det").attr("href");
    
    $("#info-num-comercial").load(url);
    

}

var validateCCC = function (){
	
	var CCC = $('#ccc-01').val() + $('#ccc-02').val() + $('#ccc-03').val() + $('#ccc-04').val();

	if (CCC.length<20 || CCC.length>20){
		alert('Faltan números en la Cuenta corriente');
		return false;
	}else{
		digit1 = 0;
		digit2 = 0;
		suma1 = 0;
		suma2 = 0;
		parc1 = 0;
		parc2 = 0;
		
		digit1 = parseFloat(CCC.substr( 8, 1));
		digit2 = parseFloat(CCC.substr(9, 1));
		
		suma1 = parseFloat(CCC.substr(0,1))*4 + parseFloat(CCC.substr(1,1))*8 + parseFloat(CCC.substr(2,1))*5;
		suma1 = suma1 + parseFloat(CCC.substr(3,1))*10 +parseFloat(CCC.substr(4,1))*9 + parseFloat(CCC.substr(5,1))*7;
		suma1 = suma1 + parseFloat(CCC.substr(6,1))*3 + parseFloat(CCC.substr(7,1))*6;
		parc1 = 11 - ( suma1 % 11);
		if (parc1 > 9){ 
			parc1= 11- parc1; 
		}
		
		suma2 = parseFloat(CCC.substr(10,1))*1 + parseFloat(CCC.substr(11,1))*2 + parseFloat(CCC.substr(12,1))*4;
		suma2 = suma2 + parseFloat(CCC.substr(13,1))*8 + parseFloat(CCC.substr(14,1))*5 + parseFloat(CCC.substr(15,1))*10;
		suma2 = suma2 + parseFloat(CCC.substr(16,1))*9 + parseFloat(CCC.substr(17,1))*7 + parseFloat(CCC.substr(18,1))*3;
		suma2 = suma2 + parseFloat(CCC.substr(19,1))*6;
		parc2 = 11 - (suma2 % 11);
		if (parc2 > 9){ 
			parc2 = 11- parc2;
		}
		
		if (digit1==parc1 && digit2==parc2){
			return true;
		}else{
			alert('Los dígitos de control NO son correctos.');
			return false;
		}
	}
}

var clickBtnCCCValidate = function (){
	if(validateCCC()){
		$('#tram-mod-ccc-form-btn-save').removeClass('disabled');
		$('#tram-mod-ccc-form-btn-save').prop('disabled', false);
		$('#ccc-valid').html('Válida!');
		$('#ccc-valid').removeClass('btn-danger');
		$('#ccc-valid').addClass('btn-success');
		return true;
	}else{
		$('#tram-mod-ccc-form-btn-save').addClass('disabled');
		$('#tram-mod-ccc-form-btn-save').prop('disabled', true);
		$('#ccc-valid').html('NO válida!');
		$('#ccc-valid').removeClass('btn-success');
		$('#ccc-valid').addClass('btn-danger');
		return false;
	}
	return false;
}

function loading_info(msg){
	var p_info = $('<p></p>');
	p_info.text(msg);
	$('#loading-info')
			.empty()
			.addClass('text-center');
	$('#loading-info')
			.append(p_info);

	var cl = new CanvasLoader('loading-info');
	cl.setColor('#3f68a6'); // default is '#000000'
	cl.setShape('roundRect'); // default is 'oval'
	cl.setDiameter(114); // default is 40
	cl.setDensity(17); // default is 40
	cl.setRange(1); // default is 1.3
	cl.setSpeed(1); // default is 2
	cl.setFPS(10); // default is 24
	cl.show(); // Hidden by default
	
	
}


