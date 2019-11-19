package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.HelpDao;
import ar.edu.itba.paw.models.help.Help;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HelpRepositoryHibernate extends SimpleRepositoryHibernate<Help> implements HelpDao {
    @Override
    public List<Help> findHelpRequestByReservationHash(long reservationId) {
        return em.createQuery("SELECT h FROM Help AS h WHERE h.reservation.id = :reservationId", Help.class).getResultList();
    }

    @Override
    public List<Help> findAllHelpRequestsNotSentFor() {
        return em.createQuery("SELECT h FROM Help AS h WHERE h.helpStep = 'UNATTENDED'", Help.class).getResultList();
    }

    @Override
    public boolean updateToHelpRequestResolved(long helpId) {
        final TypedQuery<Help> query = em.createQuery("SELECT h FROM Help AS h WHERE h.id = :helpId", Help.class);
        query.setParameter("helpId", helpId);
        final Help helpRequest = query.getSingleResult();
        if (helpRequest != null) {
            helpRequest.setHelpResolved();
            em.merge(helpRequest);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRequestToRequiresFurtherAction(long helpId) {
        final TypedQuery<Help> query = em.createQuery("SELECT h FROM Help AS h WHERE h.id = :helpId", Help.class);
        query.setParameter("helpId", helpId);
        final Help helpRequest = query.getSingleResult();
        if (helpRequest != null) {
            helpRequest.setHelpStillRequired();
            em.merge(helpRequest);
            return true;
        }
        return false;
    }

    @Override
    String getTableName() {
        return "Help ";
    }

    @Override
    Class<Help> getModelClass() {
        return Help.class;
    }
}
