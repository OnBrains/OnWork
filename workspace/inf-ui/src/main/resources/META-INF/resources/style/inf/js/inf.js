function showDlg(dlgId) {
    var wrapDlgId = '#' + dlgId;
    $(wrapDlgId).modal({backdrop: 'static', show: true});
}

function hideDlg(dlgId) {
    var wrapDlgId = '#' + dlgId;
    alert(wrapDlgId);
    $(wrapDlgId).modal('hide');
}

function computeHeight(containerId, minusHeight) {

    if (typeof minusHeight == "undefined" || minusHeight == null) {
        minusHeight = 0;
    }

    var container = "#" + containerId;

    var possibleComponentHeight = $(window).height() - $(container).offset().top - minusHeight;
    $(container).height(possibleComponentHeight);
}