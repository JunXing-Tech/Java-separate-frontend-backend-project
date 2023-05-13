import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import {GITHUB_LINK} from "@/constants";

const Footer: React.FC = () => {
  const defaultMessage = 'JunXing Design';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        /*{
          key: 'Ant Design Pro',
          title: 'Ant Design Pro（待修改）',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },*/
        {
          key: 'github',
          title: <><GithubOutlined /> JunXing GitHub</>,
          href: GITHUB_LINK,
          blankTarget: true,
        },
        /*{
          key: 'Ant Design',
          title: 'Ant Design（待修改）',
          href: 'https://ant.design',
          blankTarget: true,
        },*/
      ]}
    />
  );
};
export default Footer;
