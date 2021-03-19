// 入力欄をクリックすると発火します.
// クリック時にモーダルを表示させてください.

// 午前か午後のボックスを選択された場合の発火処理
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

// モーダルを閉じる
closeModai = () => {
	var closeBtn = document.getElementById('closeBtn');
	modal.style.display = 'none';
};

// モーダル表示処理
displayModal = (id) =>{
	var gozen = document.getElementById(id + '.gozen');
	var gogo = document.getElementById(id + '.gogo');

	// 年月日取得
	var nen = gozen.dataset.nen;
	var day = gozen.dataset.day;
	var youbi = gozen.dataset.youbi;
	document.getElementById('modalDisplayYear').innerHTML = nen;
	document.getElementById('modalDisplayDate').innerHTML = day + youbi;

	// モーダルに選択した日付の情報を表示
	var modalElementFrom = document.getElementById('modal_from');
	var modalElementTo = document.getElementById('modal_to');
	var modalElementDate = document.getElementById('modal_date');
	var modalElementWorkDayId = document.getElementById('modal_workDayId');
	var modalElementUserId = document.getElementById('modal_userId');

	modalElementFrom.value = gozen.value;
	modalElementTo.value = gogo.value;
	modalElementDate.value = gozen.dataset.ymd;
	modalElementWorkDayId.value = gozen.dataset.workdayid;
	modalElementUserId.value = document.getElementById('sendUserId').value;

	var modal = document.getElementById('modal');
	modal.style.display = 'block';
};

// 勤怠選択処理
clickKintaiSelect = () => {
	var form = document.forms['selectKintaiForm'];

	// 選択勤怠
	var kintai = document.getElementById('selectKintai').value.replace('-','');
	document.getElementById('sendYearMonth').value = kintai;
	// ユーザID
	//var userId = doument.getElementById('userId').value;
	//document.getElementById('sendUserId').value = userId;

	form.submit();


};