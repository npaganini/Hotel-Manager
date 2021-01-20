import React from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';
import BackIcon from '@material-ui/icons/Backspace';
import SaveIcon from '@material-ui/icons/Save';

const useStyles = makeStyles((theme) => ({
    button: {
        margin: theme.spacing(1),
        borderRadius: '10px',
        textAlign: 'center',
    },
    text:{
        margin: '0px !important'
    }
}));

const IconLabelButtons = (props) => {
    const classes = useStyles();

    switch (props.ButtonType) {
        case "Delete":
            return (
                <Button
                    variant="contained"
                    color="secondary"
                    className={classes.button}
                    startIcon={<DeleteIcon />}
                    id={props.Id}
                >
                    <p className={classes.text}>{props.ButtonText}</p>
                </Button>
            );

        case "Save":
            return (

                <Button
                    variant="contained"
                   className={classes.text} color="primary"
                    className={classes.button}
                    startIcon={<SaveIcon />}
                    id={props.Id}
                >
                    <p className={classes.text}>{props.ButtonText}</p>
                </Button>
            );

        case "Back":
            return (

                <Button
                    variant="contained"
                    color="default"
                    className={classes.button}
                    id={props.Id}
                >
                    <p className={classes.text}>{props.ButtonText}</p>
                </Button>
            );
        case "Inherit":
            return (

                <Button
                    variant="contained"
                    color="default"
                    size="large"
                    className={classes.button}
                    id={props.Id}
                >
                    <p className={classes.text}>{props.ButtonText}</p>
                </Button>
            );

        default:
            return (
                <div></div>
            );
    }
}

export default IconLabelButtons;