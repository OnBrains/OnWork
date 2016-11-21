package org.onbrains.onwork.inf.dataaccess.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import com.google.common.base.Strings;

/**
 * Created on 13.11.2016 19:41.
 *
 * @author Oleg Naumov
 */
public final class EntityManagerUtils {

	public static <T> List<T> queryCachedEntities(EntityManager em, Class<T> entityClass, String cacheRegion) {
		TypedQuery<T> cachedQuery = createCachedQuery(em, entityClass, null, cacheRegion);
		List<T> cachedQueryResult;
		try {
			cachedQueryResult = cachedQuery.getResultList();
		} catch (EntityNotFoundException e) {
			cachedQuery = createCachedQuery(em, entityClass, CacheMode.REFRESH, cacheRegion);
			cachedQueryResult = cachedQuery.getResultList();
		}
		return cachedQueryResult;
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private static <T> TypedQuery<T> createCachedQuery(EntityManager em, Class<T> entityClass, CacheMode cacheMode,
			String cacheRegion) {
		TypedQuery<T> query = em.createQuery(String.format("from %s", entityClass.getName()), entityClass);
		query.setHint(QueryHints.CACHEABLE, true);

		if (!Strings.isNullOrEmpty(cacheRegion))
			query.setHint(QueryHints.CACHE_REGION, cacheRegion);

		if (cacheMode != null)
			query.setHint(QueryHints.CACHE_MODE, cacheMode);

		return query;
	}

}