import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import Input from '../Input/Input'

const useStyles = makeStyles({
    wall:{
        background: '#DDA0DD',
    },
    root: {
        background: '#fbfbfb',
        borderRadius: '8px',
        boxShadow: '1px 2px 8px rgba(0, 0, 0, 0.65)',
        height: '500px',
        margin: '6rem auto 8.1rem auto',
        width: '429px',
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
    // submit-btn:hover {
    //     'box-shadow: 0px 1px 18px #24c64f',
    //   }
});

const OutlinedCard = (props) => {
    const classes = useStyles();
    const bull = <span className={classes.bullet}>•</span>;

    return (
        <div className={classes.wall}>
            <Card className={classes.root} variant="outlined">
                <CardContent>
                    <Typography className={classes.title} color="textSecondary" gutterBottom>
                        Word of the Day
        </Typography>
                    <Typography variant="h5" component="h2">
                        be{bull}nev{bull}o{bull}lent
        </Typography>
                    <Typography className={classes.pos} color="textSecondary">
                        adjective
        </Typography>
                    <Typography variant="body2" component="p">
                        well meaning and kindly.
          <br />
                        {'"a benevolent smile"'}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small">Learn More</Button>
                </CardActions>
            </Card>
        </div>
    );
}

export default OutlinedCard;