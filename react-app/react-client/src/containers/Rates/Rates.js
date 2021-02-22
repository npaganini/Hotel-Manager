import React, { useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { makeStyles } from "@material-ui/core/styles";
import { withRouter } from "react-router";

import Button from "../../components/Button/Button";
import DatePicker from "../../components/DatePickers/DatePicker";
import Input from '../../components/Input/Input'
import Table from '../../components/Table/Table'
import { useTranslation } from "react-i18next";
import { rateColumns } from "../../utils/columnsUtil";
import Progress from "../../components/Progress/Progress"

import { getAvgHotelRating, getAllHotelRatings, getAvgRoomRating, getAllRoomRatings } from "../../api/ratingsApi";




const useStyles = makeStyles((theme) => ({
    container: {
        background: "#FAF6FC",
        height: "100vh",
        maxWidth: "100%",
        padding: 0,
    },
    row: {
        paddingTop: "40px",
        paddingLeft: "10%",
        paddingRight: "10%",
        width: "100%"
    },
    buttonCol: {
        textAlign: "center",
    },
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const Rates = ({ history }) => {
    const classes = useStyles();
    const { t } = useTranslation();
    const [search, setSearch] = useState("");
    const [showDialog, updateShowDialog] = useState(false);
    const [errorStatusCode, setErrorStatusCode] = useState(200);
    const [avg, setAvg] = useState("");
    const [tableInfo, setTableInfo] = useState({ rates: [], totalCount: 0 });
    const { rates, totalCount } = tableInfo;

    const getAllRatingsFiltered = (page = 1, limit = 20) => {
        getAllHotelRatings({ page, limit })
            .then((response) => {
                setTableInfo({ rates: response.data, totalCount: +response.headers["x-total-count"] });
            })
            .catch(() => {
                updateShowDialog(true);
            }
        );

        getAvgHotelRating()
            .then((response) => {
                setAvg(response.data.rating);
            });
    };

    const searchHandler = (page = 1, limit = 20) => {
        getAllRoomRatings(search, { page, limit })
            .then((response) => {
                setTableInfo({ rates: response.data, totalCount: +response.headers["x-total-count"] });
            })
            .catch((error) => {
                updateShowDialog(true);
            }
        );

        getAvgRoomRating(search)
            .then((response) => {
                setAvg(response.data.rating);
            }
        );
    }


    const [customFunc, setCustomFunc] = useState(() => getAllRatingsFiltered)

    const searchOnChange = (newSearch) => {
        setSearch(newSearch.target.value);
    }


    const onSearchRatings = (page = 1, limit = 20) => {
        if (search.length == 0)
            setCustomFunc(() => getAllRatingsFiltered())
        else
            setCustomFunc(() => searchHandler())
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container fluid="md" className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={6}>
                        <Input label={t("ratings.roomNumber")} type="email" onChange={searchOnChange} />
                    </Col>
                    <Col xs={6} md={3} style={{ textAlign: 'right' }}>
                        <Button ButtonType="Save" onClick={onSearchRatings} ButtonText={t("search")} />
                    </Col>
                    <Col xs={6} md={3} style={{ textAlign: 'center' }}>
                        <Button ButtonType="Back" onClick={back} ButtonText={t("user.home")} />
                    </Col>
                </Row>
                <Row className={classes.row} style={{ textAlign: 'center' }}>

                </Row>
                <br />
                <Row className={classes.row}>
                    <Col xs={12} md={4} style={{ textAlign: 'center' }}>
                        {t("ratings.avgRate")}
                    </Col>
                    <Col xs={12} md={8}>
                        <Progress progress={avg} />
                    </Col>
                </Row>
                <br />
                <Row className="justify-content-sm-center" style={{ background: "#FAF6FC", width: "100%" }}>
                    <Col className={classes.tableCol}>
                        <Table columns={rateColumns} rows={rates} totalItems={totalCount} pageFunction={customFunc} />
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(Rates);
