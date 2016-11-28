function showDlg(dlgId) {
    var wrapDlgId = '#' + dlgId;
    $(wrapDlgId).modal({backdrop: 'static', show: true});
}

function hideDlg(dlgId) {
    var wrapDlgId = '#' + dlgId;
    $(wrapDlgId).modal('hide');
}