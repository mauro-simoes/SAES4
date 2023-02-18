package com.apisae.api.models.reponsepossible;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aliment")
public class Aliment {

    @Id
    private Long alim_code;

    @Column(length = 149,unique = true,nullable = false)
    private String alim_nom_fr;
    @Column(length = 90)
    private String alim_nom_sci;

    private Long alim_grp_code;
    @Column(length = 43)
    private String alim_grp_nom_fr;

    private Long alim_ssgrp_code;
    @Column(length = 49)
    private String alim_ssgrp_nom_fr;
    private Long alim_ssssgrp_code;
    @Column(length = 44)
    private String alim_ssssgrp_nom_fr;

    @Column(length = 4)
    private String Energie_R_glement_UE_N_1169_2011_kJ_100_g;
    @Column(length = 5)
    private String Energie_R_glement_UE_N_1169_2011_kcal_100_g;
    @Column(length = 4)
    private String Energie_N_x_facteur_Jones_avec_fibres_kJ_100_g;
    @Column(length = 5)
    private String Energie_N_x_facteur_Jones_avec_fibres_kcal_100_g;

    @Column(length = 6)
    private String Eau_g_100_g;

    @Column(length = 6)
    private String Prot_ines_N_x_facteur_de_Jones_g_100_g;
    @Column(length = 6)
    private String Prot_ines_N_x_625_g_100_g;

    @Column(length = 7)
    private String Glucides_g_100_g;
    @Column(length = 7)
    private String Lipides_g_100_g;
    @Column(length = 7)
    private String Sucres_g_100_g;
    @Column(length = 7)
    private String Fructose_g_100_g;
    @Column(length = 6)
    private String Galactose_g_100_g;
    @Column(length = 7)
    private String Glucose_g_100_g;
    @Column(length = 7)
    private String Lactose_g_100_g;
    @Column(length = 7)
    private String Maltose_g_100_g;
    @Column(length = 7)
    private String Saccharose_g_100_g;
    @Column(length = 7)
    private String Amidon_g_100_g;
    @Column(length = 8)
    private String Fibres_alimentaires_g_100_g;
    @Column(length = 7)
    private String Polyols_totaux_g_100_g;
    @Column(length = 8)
    private String Cendres_g_100_g;
    @Column(length = 6)
    private String Alcool_g_100_g;
    @Column(length = 8)
    private String Acides_organiques_g_100_g;

    @Column(length = 9)
    private String AG_satur_s_g_100_g;
    @Column(length = 9)
    private String AG_monoinsatur_s_g_100_g;
    @Column(length = 9)
    private String AG_polyinsatur_s_g_100_g;
    @Column(length = 9)
    private String AG_4_0_butyrique_g_100_g;
    @Column(length = 9)
    private String AG_6_0_capro_que_g_100_g;
    @Column(length = 9)
    private String AG_8_0_caprylique_g_100_g;
    @Column(length = 9)
    private String AG_10_0_caprique_g_100_g;
    @Column(length = 9)
    private String AG_12_0_laurique_g_100_g;
    @Column(length = 9)
    private String AG_14_0_myristique_g_100_g;
    @Column(length = 9)
    private String AG_16_0_palmitique_g_100_g;
    @Column(length = 9)
    private String AG_18_0_st_arique_g_100_g;
    @Column(length = 9)
    private String AG_18_1_9c_n_9_ol_ique_g_100_g;
    @Column(length = 9)
    private String AG_18_2_9c_12c_n_6_linol_ique_g_100_g;
    @Column(length = 9)
    private String AG_18_3_c9_c12_c15_n_3_alpha_linol_nique_g_100_g;
    @Column(length = 9)
    private String AG_20_4_5c_8c_11c_14c_n_6_arachidonique_g_100_g;
    @Column(length = 10)
    private String AG_20_5_5c_8c_11c_14c_17c_n_3_EPA_g_100_g;
    @Column(length = 10)
    private String AG_22_6_4c_7c_10c_13c_16c_19c_n_3_DHA_g_100_g;

    @Column(length = 6)
    private String Cholest_rol_mg_100_g;
    @Column(length = 8)
    private String Sel_chlorure_de_sodium_g_100_g;
    @Column(length = 6)
    private String Calcium_mg_100_g;
    @Column(length = 6)
    private String Chlorure_mg_100_g;
    @Column(length = 8)
    private String Cuivre_mg_100_g;
    @Column(length = 8)
    private String Fer_mg_100_g;
    @Column(length = 6)
    private String Iode_g_100_g;
    @Column(length = 6)
    private String Magn_sium_mg_100_g;
    @Column(length = 8)
    private String Mangan_se_mg_100_g;
    @Column(length = 7)
    private String Phosphore_mg_100_g;
    @Column(length = 6)
    private String Potassium_mg_100_g;
    @Column(length = 6)
    private String S_l_nium_g_100_g;
    @Column(length = 6)
    private String Sodium_mg_100_g;
    @Column(length = 8)
    private String Zinc_mg_100_g;
    @Column(length = 6)
    private String R_tinol_g_100_g;
    @Column(length = 8)
    private String Beta_Carot_ne_g_100_g;

    @Column(length = 7)
    private String Vitamine_D_g_100_g;
    @Column(length = 6)
    private String Vitamine_E_mg_100_g;
    @Column(length = 5)
    private String Vitamine_K1_g_100_g;
    @Column(length = 4)
    private String Vitamine_K2_g_100_g;
    @Column(length = 6)
    private String Vitamine_C_mg_100_g;
    @Column(length = 7)
    private String Vitamine_B1_ou_Thiamine_mg_100_g;
    @Column(length = 6)
    private String Vitamine_B2_ou_Riboflavine_mg_100_g;
    @Column(length = 6)
    private String Vitamine_B3_ou_PP_ou_Niacine_mg_100_g;
    @Column(length = 7)
    private String Vitamine_B5_ou_Acide_pantoth_nique_mg_100_g;

    @OneToMany(mappedBy = "aliment")
    private List<ReponseAliment> reponses;

}
