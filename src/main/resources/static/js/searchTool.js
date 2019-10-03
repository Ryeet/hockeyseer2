/*<![CDATA[*/

    function showAndHideAgainstContainers()
    {
        $(".againstContainer").hide();
        var selected = $("#againstSelect option:selected").val();
        if(selected == 1)
        {
            $("#teamContainer").slideDown();
        }
        else if(selected == 2)
        {
            $("#divisionContainer").slideDown();
        }
        else if(selected == 3)
        {
            $("#conferenceContainer").slideDown();
        }
        else if(selected == 4)
        {
            $("#leagueContainer").slideDown();
        }
        return null;
    }

    $(document).ready(function() {


        showAndHideAgainstContainers();
        $("#againstSelect").change(showAndHideAgainstContainers);

        $('.datepicker').datepicker({
            selectMonths: true,
            selectYears: 10,
            today: 'Today',
            clear: 'Clear',
            close: 'Ok',
            closeOnSelect: true
        });

        $('.collapsible').collapsible();


    });


/*]]>*/