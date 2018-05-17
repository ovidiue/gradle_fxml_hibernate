document.write("HELLO OVIDIU");
document.write(contact);
$("body").append("<input type='text'>");
var table = $("<table><tbody></tbody></table>");
getTable(contact);

for (var i=0; i<contact.length; i++) {
    document.write(contact[i].name);
    document.write(contact[i].surname);
}

document.write(typeof contact);
//var table = getTable(contact);
$('body').append(table);

function getTable(arrContacts) {

    if (arrContacts) {
        arrContacts.forEach(function(e){
            document.write(e.name);
            document.write(e.surname);
            table.find('tbody')
                .append('<tr>')
                .append('td');
          /*  var tr = $("<tr></tr>");
            tr.append("<td>"+e.name+"</td>");
            table.append(tr);*/
        } );
    }

    return table;

}