package com.simplesdental.desafiojavabackend.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ContatoRepositoryTest {

//    @Autowired
//    private ContatoRepository contatoRepository;
//
//    @Autowired
//    private ProfissionalRepository profissionalRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testFindByTextoAndFieldsDynamic() {
//        var profissional = Profissional.builder()
//                .nome("John Doe")
//                .cargo(ProfissionaisCargos.Desenvolvedor)
//                .nascimento(LocalDate.of(1990, 5, 15))
//                .createdDate(LocalDate.now())
//                .ativo(true)
//                .build();
//
//        profissionalRepository.save(profissional);
//
//        var contato1 = Contato.builder()
//                .nome("casa")
//                .contato("123456789")
//                .createdDate(LocalDate.now())
//                .profissional(profissional)
//                .build();
//
//        var contato2 = Contato.builder()
//                .nome("trabalho")
//                .contato("987654321")
//                .createdDate(LocalDate.now())
//                .profissional(profissional)
//                .build();
//
//        var contato3 = Contato.builder()
//                .nome("celular")
//                .contato("555555555")
//                .createdDate(LocalDate.now())
//                .profissional(profissional)
//                .build();
//
//        entityManager.persist(contato1);
//        entityManager.persist(contato2);
//        entityManager.persist(contato3);
//        entityManager.flush();
//
//        var texto = "casa";
//        var fields = List.of("nome");
//
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Map<String, Object>> result = contatoRepository.findByTextoAndFieldsDynamic(texto, fields, pageable);
//
//        assertEquals(1, result.getTotalElements());
//        var resultMap = result.getContent().get(0);
//        assertEquals(contato1.getId(), resultMap.get("id"));
//        assertEquals(contato1.getNome(), resultMap.get("nome"));
//        assertEquals(contato1.getContato(), resultMap.get("contato"));
//        assertEquals(contato1.getCreatedDate(), resultMap.get("createdDate"));
//    }
}