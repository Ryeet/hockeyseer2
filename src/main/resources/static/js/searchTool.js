/*<![CDATA[*/

    function showAndHideAgainstContainers()
    {
        console.log("changed");
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


        $("#againstSelect").change(showAndHideAgainstContainers);

    });


/*]]>*/