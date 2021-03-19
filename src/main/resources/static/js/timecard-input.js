// 入力欄をクリックすると発火します.
// クリック時にモーダルを表示させてください.

clickInputGozen = (a) => {
	var str = a
	var id = str.replace('.gozen', '');
	displayModal(id);
};

clickInputGogo = (a) => {
	var str = a
	var id = str.replace('.gogo', '');
	displayModal(id);
	
};

closeModai = () => {
	var closeBtn = document.getElementById('closeBtn');
	modal.style.display = 'none';
};

displayModal = (id) =>{
	var gozen = document.getElementById(id + '.gozen');
	var gogo = document.getElementById(id + '.gogo');
	// 年月日取得
	var nen = gozen.dataset.nen;
	var day = gozen.dataset.day;
	var youbi = gozen.dataset.youbi;
	document.getElementById('modalDisplayYear').innerHTML = nen;
	document.getElementById('modalDisplayDate').innerHTML = day + youbi;
	// モーダルに時間受け渡し
	var modalElementFrom = document.getElementById('modal_from');
	var modalElementTo = document.getElementById('modal_to');
	var modalElementDate = document.getElementById('modal_date');
	modalElementFrom.value = gozen.value;
	modalElementTo.value = gogo.value;
	modalElementDate.value = nen + day;
	var modal = document.getElementById('modal');
	modal.style.display = 'block';
}