import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const TAX_RATE = 0.07;

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});

function ccyFormat(num) {
  return `${num.toFixed(2)}`;
}

function priceRow(qty, unit) {
  return qty * unit;
}

function createRow(desc, qty, unit) {
  const price = priceRow(qty, unit);
  return { desc, qty, unit, price };
}

function subtotal(items) {
  return items
    .map(({ productAmount, productPrice }) => productAmount * productPrice)
    .reduce((sum, i) => sum + i, 0);
}

const rowsDefault = [
  createRow("Paperclips (Box)", 100, 1.15),
  createRow("Paper (Case)", 10, 45.99),
  createRow("Waste Basket", 2, 17.99),
];

const invoiceSubtotal = subtotal(rowsDefault);
const invoiceTaxes = TAX_RATE * invoiceSubtotal;
const invoiceTotal = invoiceTaxes + invoiceSubtotal;

const ExpensesTable = (props) => {
  const classes = useStyles();

  console.log("expenses table", props.rows);

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="spanning table">
        <TableHead>
          <TableRow>
            <TableCell align="center" colSpan={3}>
              Details
            </TableCell>
            <TableCell align="right">Price</TableCell>
          </TableRow>
          <TableRow>
            <TableCell>Desc</TableCell>
            <TableCell align="right">Qty.</TableCell>
            <TableCell align="right">Unit</TableCell>
            <TableCell align="right">Sum</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.rows.map(
            ({ productAmount, productDescription, productPrice }) => {
              return (
                <TableRow key={productDescription}>
                  <TableCell>{productDescription}</TableCell>
                  <TableCell align="right">x{productAmount}</TableCell>
                  <TableCell align="right">${productPrice}</TableCell>
                  <TableCell align="right">
                    ${priceRow(productAmount, productPrice)}
                  </TableCell>
                </TableRow>
              );
            }
          )}
          <TableRow>
            <TableCell />
            <TableCell style={{ textAlign: "right" }} colSpan={2}>
              Total
            </TableCell>
            <TableCell align="right">${subtotal(props.rows)}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default ExpensesTable;
