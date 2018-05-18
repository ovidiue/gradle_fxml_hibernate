function createTableFromArr(arrObj, objKeys = []) {
    var table = $("<table></table>")
        .append("<thead></thead>")
        .append("<tbody></tbody>");

    if (arrObj && arrObj.length) {
        if (objKeys && objKeys.length) {

            arrObj.forEach(e => {

                var tr = $("<tr></tr>");

                for (var key of objKeys) {

                    if (e[key] && e[key] !== null && typeof e[key] !== "undefined") {
                        tr.append("<td>" + e[key] + "</td>");
                    }

                }

                table.find('tbody').append(tr);
            });
        }
    }

    return table;
}

alert($('body'));

//document.write(contact);

//document.write($('body'));

var table = createTableFromArr(contact, ['name', 'surname']);

$("body").append(table);
document.write(table);


