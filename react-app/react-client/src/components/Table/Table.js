import React, {useEffect} from "react";
import {useHistory, useLocation} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import {useTranslation} from "react-i18next";
import {useQuery} from "../../utils/hooks/useQuery";
import PropTypes from "prop-types";
import Button from "../Button/Button";


const useStyles = makeStyles({});

const DataTable = ({columns, rows, totalItems = 0, pageFunction = () => {}} = {}) => {
    const classes = useStyles();
    const query = useQuery();
    const page = +query.get("page") || 1;
    const rowsPerPage = +query.get("limit") || 20;
    const {t} = useTranslation();
    const location = useLocation();
    const history = useHistory();

    useEffect(() => {
        pageFunction(page, rowsPerPage)
    }, [page, rowsPerPage]);

    const handleChangePage = (event, newPage) => {
        history.push(`${location.pathname}?page=${newPage + 1}&limit=${rowsPerPage}`);
    };

    const handleChangeRowsPerPage = (event) => {
        let newRowsPerPage = +event.target.value;
        history.push(`${location.pathname}?page=${page}&limit=${newRowsPerPage}`);
    };

    let isValidPageNumber = !((page - 1) * rowsPerPage >= totalItems);
    return (
        <div>
            <TableContainer>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead className={classes.space}>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{minWidth: column.minWidth, textAlign: 'center'}}
                                >
                                    {t(column.label)}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody className={classes.space}>
                        {rows.map((row) => {
                            return (
                                <TableRow hover role="checkbox" tabIndex={-1} key={row.id} aria-checked={"false"}>
                                    {columns.map((column) => {
                                        const value = row[column.id];
                                        if (column.isButton) {
                                            return (
                                                <TableCell key={column.id} align={column.align}
                                                           style={{textAlign: 'center'}}>
                                                    <Button
                                                        id={column.id}
                                                        ButtonType="Save"
                                                        size="large"
                                                        onClick={value}
                                                        ButtonText={t(column.label)}
                                                    />
                                                </TableCell>
                                            );
                                        } else {
                                            return (
                                                <TableCell key={column.id} align={column.align}
                                                           style={{textAlign: 'center'}}>
                                                    {
                                                        (typeof value == "boolean") ? (value ? t("yes") : t("no")) :
                                                        (Array.isArray(value) ?
                                                            value.map((product) => {
                                                                return (
                                                                    <div key={`${product[0]}${product[1]}`}>
                                                                        {`${product[0]} x${product[1]}`}
                                                                    </div>
                                                                );
                                                            })
                                                            :
                                                            (column.format ? column.format(value) : value)
                                                        )
                                                    }
                                                </TableCell>
                                            );
                                        }
                                    })}
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </TableContainer>
            {isValidPageNumber &&
                <TablePagination
                    rowsPerPageOptions={[10, 20]}
                    component="div"
                    count={+totalItems}
                    rowsPerPage={rowsPerPage}
                    page={+page - 1}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
            }
        </div>
    );
};

DataTable.propTypes = {
    columns: PropTypes.array.isRequired,
    rows: PropTypes.array.isRequired,
    totalItems: PropTypes.number.isRequired,
    pageFunction: PropTypes.func.isRequired,
};

export default DataTable;
