(function ($) {
    $.fn.extend({
        paginator: function (options) {
            var settings = $.extend({
                totalrecords: 0,
                recordsperpage: 0,
                pagebtncount: 10,
                next: 'Next',
                prev: 'Prev',
                first: 'First',
                last: 'Last',
                go: 'Go',
                theme: '',
                controlsalways: false,
                showpageinput: false,
                showrangelabel: false,
                initval: 1,
                onchange: null
            }, options);
            return this.each(function () {
                var currentPage = 0;
                var startPage = 0;
                var container = $(this).addClass('paginator').addClass(settings.theme);
                
                //empty container
                container.empty();

                
                if (settings.recordsperpage == 0) return;
                var totalpages = parseInt(settings.totalrecords / settings.recordsperpage);
                if (settings.totalrecords % settings.recordsperpage > 0) totalpages++;
                
                // not show paginator when totalpages <= 1
                if (totalpages<=1) return;

                var initialized = false;
                var list = $('<ul/>');
                var btnPrev = $('<div/>').text(settings.prev).click(function () { if ($(this).hasClass('disabled')) return false; currentPage = parseInt(list.find('li a.active').text()) - 1; navigate(--currentPage); }).addClass('btn prev');
                var btnNext = $('<div/>').text(settings.next).click(function () { if ($(this).hasClass('disabled')) return false; currentPage = parseInt(list.find('li a.active').text()); navigate(currentPage); }).addClass('btn next');
                var btnFirst = $('<div/>').text(settings.first).click(function () { if ($(this).hasClass('disabled')) return false; currentPage = 0; navigate(0); }).addClass('btn first');
                var btnLast = $('<div/>').text(settings.last).click(function () { if ($(this).hasClass('disabled')) return false; currentPage = totalpages - 1; navigate(currentPage); }).addClass('btn last');
                var inputPage = $('<input/>').attr('type', 'text').keydown(function (e) {
                    if (e.which >= 48 && e.which < 58) {
                        var value = parseInt(inputPage.val() + (e.which - 48));
                        if (!(value > 0 && value <= totalpages)) e.preventDefault();
                    } else if (!(e.which == 8 || e.which == 46)) e.preventDefault();
                });
                var btnGo = $('<input/>').attr('type', 'button').attr('value', settings.go).addClass('btn go').click(function () { if (inputPage.val() == '') return false; else { currentPage = parseInt(inputPage.val()) - 1; navigate(currentPage); } });
                var pageJumpControls = $('<div/>').addClass('pagejump').append(inputPage).append(btnGo);
                
                container.append(btnFirst).append(btnPrev).append(list).append(btnNext).append(btnLast).append(pageJumpControls);
                
                if (!settings.showpageinput) {
                	pageJumpControls.css('display', 'none');
                }
                buildNavigation(startPage);
                if (settings.initval == 0) settings.initval = 1;
                currentPage = settings.initval - 1;
                navigate(currentPage);
                initialized = true;
                function showRangeLabel(pageIndex) {
                    container.find('span').remove();
                    
                    var upper = (pageIndex + 1) * settings.recordsperpage;
                    if (upper > settings.totalrecords) upper = settings.totalrecords;
                    container.append($('<span/>').append($('<b/>').text(pageIndex * settings.recordsperpage + 1)))
                                             .append($('<span/>').text('-'))
                                             .append($('<span/>').append($('<b/>').text(upper)))
                                             .append($('<span/>').text('/'))
                                             .append($('<span/>').append($('<b/>').text(settings.totalrecords)));
                }
                function buildNavigation(startPage) {
                    list.find('li').remove();
                    for (var i = startPage; i < startPage + settings.pagebtncount; i++) {
                        if (i == totalpages) break;
                        list.append($('<li/>')
                                    .append($('<a>').attr('id', (i + 1)).addClass(settings.theme).addClass('normal')
                                    .attr('href', 'javascript:void(0)')
                                    .text(i + 1))
                                    .click(function () {
                                    	if($(this).children().hasClass("active")) return;
                                        currentPage = startPage + $(this).closest('li').prevAll().length;
                                        navigate(currentPage);
                                    }));
                    }
                    if(settings.showrangelabel) showRangeLabel(startPage);
                    inputPage.val((startPage + 1));
                    list.find('li a').addClass(settings.theme).removeClass('active');
                    list.find('li:eq(0) a').addClass(settings.theme).addClass('active');
                    
                    showRequiredButtons(currentPage);
                }
                function navigate(topage) {
                    //make sure the page in between min and max page count
                    var index = topage;
                    var mid = settings.pagebtncount / 2;
                    if (settings.pagebtncount % 2 > 0) mid = (settings.pagebtncount + 1) / 2;
                    var startIndex = 0;
                    if (topage >= 0 && topage < totalpages) {
                        if (topage >= mid) {
                            if (totalpages - topage > mid)
                                startIndex = topage - (mid - 1);
                            else if (totalpages > settings.pagebtncount)
                                startIndex = totalpages - settings.pagebtncount;
                        }
                        buildNavigation(startIndex); 
                        if(settings.showrangelabel) showRangeLabel(currentPage);
                        list.find('li a').removeClass('active');
                        inputPage.val(currentPage + 1);
                        list.find('li a[id="' + (index + 1) + '"]').addClass('active');
                        var recordStartIndex = currentPage * settings.recordsperpage;
                        var recordsEndIndex = recordStartIndex + settings.recordsperpage;
                        if (recordsEndIndex > settings.totalrecords)
                            recordsEndIndex = settings.totalrecords % recordsEndIndex;
                        if (initialized) {
                            if (settings.onchange != null) {
                                settings.onchange((currentPage + 1), recordStartIndex, recordsEndIndex);
                            }
                        }
                        showRequiredButtons();
                    }
                }
                function showRequiredButtons() {
                    if (totalpages > 1) {
                        if (currentPage > 0) {
                            if (!settings.controlsalways) {
                                btnPrev.css('visibility', 'visible');
                            }
                            else {
                                btnPrev.css('visibility', 'visible').removeClass('disabled');
                            }
                        }
                        else {
                            if (!settings.controlsalways) {
                                btnPrev.css('visibility', 'hidden');
                            }
                            else {
                                btnPrev.css('visibility', 'visible').addClass('disabled');
                            }
                        }
                        
                        if (currentPage == totalpages - 1) {
                            if (!settings.controlsalways) {
                                btnNext.css('visibility', 'hidden');
                            }
                            else {
                                btnNext.css('visibility', 'visible').addClass('disabled');
                            }
                        }
                        else {
                            if (!settings.controlsalways) {
                                btnNext.css('visibility', 'visible');
                            }
                            else {
                                btnNext.css('visibility', 'visible').removeClass('disabled');
                            }
                        }
                        
                    }
                    else {
                        if (!settings.controlsalways) {
                            btnFirst.css('visibility', 'hidden');
                            btnPrev.css('visibility', 'hidden');
                            btnNext.css('visibility', 'hidden');
                            btnLast.css('visibility', 'hidden');
                        }
                        else {
                            btnFirst.css('visibility', 'visible').addClass('disabled');
                            btnPrev.css('visibility', 'visible').addClass('disabled');
                            btnNext.css('visibility', 'visible').addClass('disabled');
                            btnLast.css('visibility', 'visible').addClass('disabled');
                        }
                    }
                    
                    if (btnFirst.text()=="") btnFirst.css('visibility', 'hidden');
                    if (btnPrev.text()=="") btnPrev.css('visibility', 'hidden');
                    if (btnNext.text()=="") btnNext.css('visibility', 'hidden');
                    if (btnLast.text()=="") btnLast.css('visibility', 'hidden');
                }
            });
        }
    });
})(jQuery);