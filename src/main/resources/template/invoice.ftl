<html>
<head>
    <title>Invoice Report for: ${invoice.customerName}</title>
    <link rel="stylesheet" href="invoice.css">
</head>
<body>
<h1>Invoice Report for: ${invoice.customerName}</h1>

<h4>Invoice Date: ${invoice.invoiceDate}</h4>

<table>
    <tr>
        <th>Sender Address</th>
        <th>Destination Number</th>
        <th>Date of Message</th>
        <th>Length of Message</th>
        <th>Message Status</th>
        <th>Message Cost (p)</th>
    </tr>
    <tbody>
    <#list invoice.invoiceItemList as invoiceItem>
        <tr>
            <td>${invoiceItem.senderAddress}</td>
            <td>${invoiceItem.destinationNumber}</td>
            <td>${invoiceItem.dateOfMessage}</td>
            <td>${invoiceItem.lengthOfMessage}</td>
            <td>${invoiceItem.messageStatus}</td>
            <td>${invoiceItem.cost}</td>
        </tr>
    </#list>
    <tr>
        <td colspan="6" class="bold">
            Total Cost: ${invoice.totalCost}p
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>