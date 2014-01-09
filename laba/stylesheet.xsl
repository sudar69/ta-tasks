<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
  <html>
   <head>
    <title>Test results</title>
    <style type="text/css">
    html,body{margin:15}
    table {
     max-width: 100%;
     background-color: #ffffff;
     border-collapse: collapse;
     border-spacing: 0;
    }
    .table {
     width: 100%;
     margin-bottom: 14pt;
    }
    .table th,
    .table td {
     padding: 8px;
     font-size: 14px;
     line-height: 14pt;
     vertical-align: top;
     border-bottom: 1px #eaeaea solid;
    }
    .table.hovered tbody tr:hover {
     background-color: rgba(28, 183, 236, 0.1);
    }
    .descr {
    padding-left: 40px;
    }
    .edescr {
    padding-left: 20px;
    }
    .block {
    padding-left: 20px;
    background-repeat: no-repeat;
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2RpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0NjMwNTBBMUE1MDBFMTExOEFGQkExNjA1RkZEMzREMSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDozQzY2RjAxRDQwRUMxMUUxQjAzQzlGOEU2ODhCOTg1RiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDozQzY2RjAxQzQwRUMxMUUxQjAzQzlGOEU2ODhCOTg1RiIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M1IFdpbmRvd3MiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4MjEyMDhBRDU0NDBFMTExQkVBMkQzMTc1RTFBRDFGRSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0NjMwNTBBMUE1MDBFMTExOEFGQkExNjA1RkZEMzREMSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PlLO7b8AAAK1SURBVHjabJPfS5NRGMe/ezfn5mbqXDmc5qabQiWk1pp1kSyoiZB0300ERlAEddOFl150kxAIQf9AF3WjUJlWJHhhs1IQpOXGdExr6X7lO7f3196eM17Fhi98Du8553m+58tznmN41m5HxddFjBCXibPa2jIxR7wgwoeDDRXJD4mx3qFrZpvNCqtRV17kRbUvneb7vr95f5emo8T4foJOc2AkJr1+X9Dd4YSoqFj/GUMyEisH2Vqa4T7diRqjHrHoJtYWQtO0PEyI+sGGGhbz1Nvvv+l126GDig8vp8Tsr+QtWRDuE+O72ztz8dVwM8y1Lk+7A7pamyedSFgob4ajoZu453IegyRJUIQiLt0YZI6YXZ7YJKaIgWho8cla9DfanHXs0AfEKSZwu/vKgD67p2L29YySKQDVcgGdF/39tPeOqDtUo1ESmU/zCs4EBvQslwkELVSseDjKAoYX386G4tsFNFk5ePwXKkUUJrKxGobVxFIRZKMbxTxSCeYU88TVyMKX0FZWgr1aRofvfKXIci75B2pxj/07yzKSKAKqum8zx0TIaiiZB+q5Itzn+pjIjCaiqKUSZEFksXomEJdKelgbbdAKeiAS+/otlBIMqJV5uHp6fJpIoMFJV10qnx1n43Re0eF4awtbGGOqh0XWl5ZCWdUEczGDLl+vz952crLxhA15uex4lvVBIrO5NdJo5rjqpmYXv7PDrvCjJiIQr3LJZKC+tdWpZrZh4kpQhQKiKz9YQe8wByvEc95ghUng4ej0Pqb5Z+I64SDMxERkaUVUSipkUUJez3oIEyz3v1Zu8niCJlWiRjDjb24XfCpVtlHncNC1GaFKAoq6KmrxyEErc5pVVtJB2ni0Ed0oKJwBFrMRdktVGZNBB9lgBNtjMSxWyzl4TEc950DFc/501HP+J8AAVr8TzxYnAmEAAAAASUVORK5CYII=);
    }
    .pass {
    padding-left: 20px;
    background-repeat: no-repeat;
    background-image: url(data:image/gif;base64,R0lGODlhEAAQAPcAAAAAADVqAD12Az1xCUSCBkaFBkiFDEmOA02KD06YA1GYCVmOJFuGMF2RKl+ZJ22fAGaUI2GaKmqZJXSeLnahHneiHnmkHn2pHn+wAm+rM3yqL36rMHqzJXm0I2+XSHyxSICyAICyAYO1AIa3BoOyCYS1CYa5AIa6AIm9AI2/CI2/CYu3GZC/FZC/FpK+HoKnPIevMIW4Loa5L4u1LpO/IZK9JJS7LpO/MYytbI+vb4+sco6xbJCyb5W8b5i+cozBAI3BAo3CA4/FAJDEApDFA5HIAJPLAZPJB5XMAJfPAJXKCJbKCZfMCJTBHpbFHprSAJrSAZnQBp3VAp7UCZ/WCZ7QFovBLpbCIZjGIZnCL5vJIZ3MIZ3JLp/KLZ/LLpXBMZnBMJrIMabZGaHNLaLTLqfVL6jYJKnZJa/dLqDRMKnWMJfAb5nBcpzFcqW9ja7PjdfjwOLuw+Tww+rw3uzx4ezy4fD24f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAP8ALAAAAAAQABAAAAjvAP8JFNgmQ4IEGdoMXPjvjQIradCgSWNFwRuGHzqQEUNFihQqYsh0+DBwDQczU6A8WfnECJMzHNgIPKAmSpKbN4tYsHCkzIF/PWRUQSLkgRCiFubUuVAlhg8HYZT8qACHwokKc+7ckbMkTIQCY4Y4yUrnRdY7drYQ8YKAQBcgWuxonWtHy48gXAws+JIChRO5Wu1gQYFCxY0GOzawMGGiiVw7VxibaKGBxz8BWUaIEOEiDo3NIkaAESAQh4QaJUKAWA0iRIkaEnIM9ABhxgoSGDCQWDEDggeGbgZMgGHDBowJA9wwHKiDQYAADHQwDAgAOw==);
    }
    .fail{
    padding-left: 20px;
    background-repeat: no-repeat;
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2RpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0NjMwNTBBMUE1MDBFMTExOEFGQkExNjA1RkZEMzREMSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDowRDc3M0E1QjJBNEYxMUUxQTQwM0JCNzM4QUQyOEQ1RSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDowRDc3M0E1QTJBNEYxMUUxQTQwM0JCNzM4QUQyOEQ1RSIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M1IFdpbmRvd3MiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDoxNDFEQkQxRjAzMjhFMTExOTUwRjk5NUE1RUQyREIwNSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0NjMwNTBBMUE1MDBFMTExOEFGQkExNjA1RkZEMzREMSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pl7LH18AAAK2SURBVHjabFNdSFNhGH529uc2ZXObokkXmcup+LfUFAIhQszo5y6CuqrMQMq66CK67DZBIqII6kKIoC5CClEKii7CCzP7001M54Y/285+nG475+yc3u/YJKff4Tl853mf9z3fec/7aIYqnchb1YReQieh6R83RfhIeEKY/V+syRXQcBwUWb5F23ueUz0mZ3kJrHarGovzcYSXQ5gceZeix7ukHSTtjgIGwhtXe1t3VVMthBiPQrMRSlbcEmn1SG5mYLDZMTf1C74vE6NEnyEI2hPFZqa57+pov1DbUofA9HdEJQOSQT8KiyyQszKW5hYQlQuQDi7C5amHrDVW8YGAhfLGOLrVE/qrPXUIeWdhPdqDrkfDsHWepsRFFWzPOBYL+bw41FzDXnqDUMtOcKe+63iHWSOh6GA13H231WOXHT6CRDQBU2UNGq9c3+YyC7MQQqswlVdwa/N/Mjriu23UrEwiglQkhJW3L1F28pyakEvMLRaL/ZgEpzfA5nAwqpsVOKDlFKQ3koCiwP/6GdYT63Cdv7wj2ffiKaLjr7aaKorQFxWzbQUrgKwgQswIagE+HIM+nsyfDSSJWw2uwe60qb+cEyVGa1kT/amUgKyk0Devo+LsRTT3DewqwDgWYxqJtOlUhtF+VmA0Ts1StDqUNrfCc+3mdtI8HZsht1iMaZg2zicYNc7+QoBfCvTuc7u52MxPKHQ5GlrgG34M7/OHCH+dAOjIOS4wNgJjSRl+ffqcpQJXc5P4oNzt7rdbLUiH12CraUBkamLHJzia2hD7PY0CZyn4+AaWZ2aGiB7ITeL7ZDjcypksVWZnCVKrQWqsAFkSmT9olHUQN5PQFTvBR2JY8XrZKF9i/dfkuVE10/6GRpPRoIOO2yIl8k1GkLA0/U01E2Fwlxv3sPOxPDt/2MvOfwUYAKhlHNHvj6QBAAAAAElFTkSuQmCC);
    }
    </style>
<script type="text/javascript">window.onload=function(){var a=document.querySelectorAll('p.status');for(var i=0;i!=a.length;i++){switch(a[i].innerHTML){case'0':{a[i].innerHTML='Fail';a[i].className+=' fail';break}case'1':{a[i].innerHTML='Pass';a[i].className+=' pass';break}default:{}}}var x=document.querySelectorAll('p.edescr');for(var i=0;i!=x.length;i++){if(x[i].innerHTML=='-'){x[i].innerHTML='No errors';x[i].className+=' pass'}else{x[i].className+=' block'}}}</script>
   </head>
   <body>
    <h2>Test results</h2>
    <h4>
     <xsl:value-of select="testCaseList/timeInfo" />
    </h4>
    <h4>
     Testov vupolneno - <xsl:value-of select="testCaseList/size" />
    </h4>
    <h4>
     Proverok vupolneno - <xsl:value-of select="testCaseList/checksize" />
    </h4>
    <table class="table hovered">
     <xsl:for-each select="testCaseList/ListMessage/entry">
      <tr>
       <td  colspan='2'>
        <p>
         <b>
          <xsl:value-of select="key" />
         </b>
        </p>
        <p>
         Start test: <xsl:value-of select="value/startDate" /> - End test: <xsl:value-of select="value/finishDate" />
        </p>
        <p class='edescr'>
         <xsl:value-of select="value/errorDescription" />
        </p>
       </td>
      </tr>
        <xsl:for-each select="value/testMessage">
            <tr>
             <td>
              <p class='descr'>
               <xsl:value-of select="Description" />
              </p>
             </td>
             <td>
              <p class='status'>
               <xsl:value-of select="Status" />
              </p>
             </td>
            </tr>
        </xsl:for-each>
     </xsl:for-each>
    </table>
   </body>
  </html>
 </xsl:template>
</xsl:stylesheet>