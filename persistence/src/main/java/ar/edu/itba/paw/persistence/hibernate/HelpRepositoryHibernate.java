package ar.edu.itba.paw.persistence.hibernate;

import ar.edu.itba.paw.interfaces.daos.HelpDao;
import ar.edu.itba.paw.models.charge.Charge;
import ar.edu.itba.paw.models.dtos.PaginatedDTO;
import ar.edu.itba.paw.models.help.Help;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HelpRepositoryHibernate extends SimpleRepositoryHibernate<Help> implements HelpDao {
    @Override
    public PaginatedDTO<Help> findHelpRequestsByReservationHash(long reservationId, int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Help> entityRoot = cqCount.from(Help.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate = builder.equal(entityRoot.get("reservation").get("id"), reservationId);
        cqCount.where(builder.and(wherePredicate));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Help> helpList = em.createQuery("SELECT h FROM Help AS h WHERE h.reservation.id = :reservationId", Help.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(helpList, count);
    }

    @Override
    public PaginatedDTO<Help> findAllHelpRequestsNotSentFor(int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Help> entityRoot = cqCount.from(Help.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate = builder.equal(entityRoot.get("helpStep"), "UNATTENDED");
        cqCount.where(builder.and(wherePredicate));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Help> helpList = em.createQuery("SELECT h FROM Help AS h WHERE h.helpStep = 'UNATTENDED'", Help.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(helpList, count);
    }

    @Override
    public PaginatedDTO<Help> findAllHelpRequestsNotResolved(int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<Help> entityRoot = cqCount.from(Help.class);
        cqCount.select(builder.count(entityRoot));
        Predicate wherePredicate = builder.equal(entityRoot.get("helpStep"), "REQUIRES_FURTHER_ACTION");
        cqCount.where(builder.and(wherePredicate));
        long count = em.createQuery(cqCount).getSingleResult();

        List<Help> helpList = em.createQuery(
                "SELECT h FROM Help AS h WHERE h.helpStep = 'UNATTENDED' OR h.helpStep = 'REQUIRES_FURTHER_ACTION'", Help.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PaginatedDTO<>(helpList, count);
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
    String getModelName() {
        return Help.NAME + " ";
    }

    @Override
    Class<Help> getModelClass() {
        return Help.class;
    }
}
