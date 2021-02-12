import React from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import {useTranslation} from "react-i18next";
import Button from '@material-ui/core/Button';
import PropTypes from 'prop-types';
import {DialogActions, DialogContent} from "@material-ui/core";

const InfoSimpleDialog = (props) => {
    const {onClose, children, open, title} = props;
    const {t} = useTranslation();

    return (
        <Dialog onClose={onClose} aria-labelledby="simple-dialog-title" open={open}>
            <DialogTitle id="simple-dialog-title">{title}</DialogTitle>
            <DialogContent>{children}</DialogContent>
            <DialogActions>
                {onClose && <Button onClick={onClose} color="primary">
                    {t('close')}
                </Button>}
            </DialogActions>
        </Dialog>
    );
}

InfoSimpleDialog.propTypes = {
    onClose: PropTypes.func,
    open: PropTypes.bool.isRequired,
    title: PropTypes.string.isRequired,
};

export default InfoSimpleDialog;
