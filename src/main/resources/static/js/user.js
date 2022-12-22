$(function () {
    function createRoles(roles) {
        return roles.map(el => `<span class="mx-1">${el.viewName}</span>`).join('')
    }

    function renderTableUser1() {
        $.ajax({
            url: `/rest/user`,
            type: 'GET',
            dataType: 'json',
            success: (response) => {
                const html = createRowUser1(response)
                $('.js-tableUser').html(html)
                console.log(response)
            }
        })
    }

    function createRowUser1(data) {
        return `
            <tr class="js-row">           
                <td class="js-row-item" data-name="id">${data['id']}</td>
                <td class="js-row-item" data-name="username">${data['username']}</td>
                <td class="js-row-item" data-name="lastName">${data['lastName']}</td>
                <td class="js-row-item" data-name="age">${data['age']}</td>
                <td class="js-row-item" data-name="email">${data['email']}</td>               
                <td class="js-row-roles">
                    ${createRoles(data['roles'])}
                </td>                
            </tr>
        `
    }

    function renderHeader() {
        $.ajax({
            url: `/rest/user`,
            type: 'GET',
            dataType: 'json',
            success: (response) => {
                const html = createHeader(response)
                $('.js-header').html(html)
                console.log(response)
            }
        })
    }

    function createHeader(data) {
        return `   
                    <span class="font-weight-bold">${data['email']}</span>
                    <span> with roles:</span>
                    <span class="mx-1"> ${createRoles(data['roles'])} </span>
      `
    }
    renderHeader()
    renderTableUser1()
})