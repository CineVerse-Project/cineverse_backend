package fa.cineverse.common;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * IdentityCodeGenerator
 *
 * @Day: 5/30/2023 9:09 AM
 *
 * @Version 1.0
 *
 * Copyright
 *
 * Modification Logs:
 * DATE          AUTHOR       DESCRIPTION
 * ---------------------------------------
 * 5/30/2023      AnP1          Create
 */
public class IdentityCodeGenerator implements IdentifierGenerator {

    private String prefix;


    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:06 AM
     * @Return: java.io.Serializable
     * @Params: [session, obj]
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        String queryString = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        List<Long> longs = new ArrayList<>();
        longs.add(0L);
        Query query = session.createQuery(queryString);
        for (Object o : query.list()) {
            longs.add(Long.parseLong(((String) o).replace(prefix + "-", "")));
        }
        Long max = Collections.max(longs);
        if (obj.getClass().getName().contains("Booking") || obj.getClass().getName().contains("Ticket")
                || obj.getClass().getName().contains("Payment")) {
            return prefix + "-" + String.format("%05d", (max + 1));
        } else {
            return prefix + "-" + String.format("%04d", (max + 1));
        }

    }

    /**
     * @Author: AnP1
     * @Day: 5/30/2023 9:06 AM
     * @Return: void
     * @Params: [type, properties, serviceRegistry]
     */
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }

}