import React, {useState} from "react";
import {Container, Row, Col} from "react-bootstrap";
import {makeStyles} from "@material-ui/core/styles";
import {withRouter} from "react-router";
import Button from "../../components/Button/Button";
import Table from '../../components/Table/Table'
import {useTranslation} from "react-i18next";
import {getAllHelpRequests} from "../../api/helpApi";
import {helpListColumns} from "../../utils/columnsUtil";


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
    },
    tableCol: {
        paddingRight: "7.5%",
        paddingLeft: "7.5%",
    },
}));

const HelpRequest = ({history}) => {
    const classes = useStyles();
    const {t} = useTranslation();
    const [tableInfo, setTableInfo] = useState({helpList: [], totalCount: 0});
    const {helpList, totalCount} = tableInfo;
    // const {itemList, totalCount, pageFunction} = usePagination(getAllHelpRequests);

    const getAllHelpRequestsUnsolved = (page, limit) => {
        getAllHelpRequests({page, limit})
            .then((response) => {
                setTableInfo({helpList: response.data, totalCount: +response.headers["x-total-count"]})
            }).catch((error) => {
                console.log("There was an error while fetching all help requests! ", error);
            }
        );
    };

    const onSubmitHelpRequest = () => {
        console.log(history);
        history.push("/help")
    }

    const back = () => {
        history.push("/");
    }

    return (
        <div>
            <Container className={classes.container}>
                <Row className={classes.row}>
                    <Col xs={12} md={10} className={classes.tableCol}>
                        <Table columns={helpListColumns} rows={helpList} totalItems={totalCount}
                               pageFunction={getAllHelpRequestsUnsolved}/>
                    </Col>
                    <Col xs={12} md={2}>
                        <Col xs={12} md={6} style={{textAlign: 'left'}}>
                            <Button ButtonType="Save" size="large" onClick={onSubmitHelpRequest}
                                    ButtonText={t("refresh")}/>
                        </Col>
                        <Col xs={12} md={6} style={{textAlign: 'left'}}>
                            <Button ButtonType="Back" size="large" onClick={back} ButtonText="Volver"/>
                        </Col>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default withRouter(HelpRequest);
